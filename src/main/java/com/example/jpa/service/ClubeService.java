package com.example.jpa.service;

import com.example.jpa.dto.ClubeDTO;
import com.example.jpa.model.Clube;
import com.example.jpa.repository.ClubeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class ClubeService {
    @Autowired
    private ClubeRepository clubeRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClubeService.class);


    public ResponseEntity addClubes(List<ClubeDTO> clubeDTOList) {

        clubeDTOList.forEach(
                clubeDTO -> {
                    Clube clube = new Clube(clubeDTO);
                    clubeRepository.save(clube);
                }
        );

        return ResponseEntity.ok("Clubes adicionados com sucesso!");
    }

    public ResponseEntity getByEstado(String estado) {

        long startTime, endTime;
        long minTime, maxTime;
        long timeTaken;

        // Uso de Query Methods
        startTime = System.currentTimeMillis();
        List<Clube> QueryMethods = clubeRepository.findByEstado(estado);
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        minTime = timeTaken;
        maxTime = timeTaken;
        logger.info("Tempo levado no Query Methods: " + timeTaken + "ms");

        //  Uso de JPQL (Java Persistence Query Language)
        startTime = System.currentTimeMillis();
        List<Clube> EstadoUsingJPQL = clubeRepository.findByEstadoUsingJPQL(estado);
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        minTime = Math.min(minTime, timeTaken);
        maxTime = Math.max(maxTime, timeTaken);
        logger.info("Tempo levado no JPQL: " + timeTaken + "ms");

        // Check the time taken by Native Query
        startTime = System.currentTimeMillis();
        List<Clube> EstadoUsingNative = clubeRepository.findByEstadoUsingNative(estado);
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        minTime = Math.min(minTime, timeTaken);
        maxTime = Math.max(maxTime, timeTaken);
        logger.info("Tempo levado no Native Query: " + timeTaken + "ms");

        // Uso de Query Methods
        startTime = System.currentTimeMillis();
        Clube exampleClube = new Clube();
        exampleClube.setEstado("SP");
        Example<Clube> example = Example.of(exampleClube);
        List<Clube> EstadoExample = clubeRepository.findAll(example);
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        minTime = Math.min(minTime, timeTaken);
        maxTime = Math.max(maxTime, timeTaken);
        logger.info("Tempo levado no Example: " + timeTaken + "ms");

        return ResponseEntity.ok("Minimum time: " + minTime + "ms, Maximum time: " + maxTime + "ms");

    }

    public ResponseEntity getAll() {
        return ResponseEntity.ok(clubeRepository.findAll());
    }


}
