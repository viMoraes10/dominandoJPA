package com.example.jpa.controller;

import com.example.jpa.dto.ClubeDTO;
import com.example.jpa.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "clubes")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @PostMapping(value = "/add-clubes")
    public ResponseEntity<?> addClube(@RequestBody List<ClubeDTO> clubeDTO){
        try{
            return ResponseEntity.ok(clubeService.addClubes(clubeDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    @GetMapping(value = "/estado/{estado}")
    public ResponseEntity<?> get1(@PathVariable String estado){
        try{
            return ResponseEntity.ok(clubeService.get1(estado));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }
}
