package com.example.jpa.repository;

import com.example.jpa.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

    List<Clube> findByEstado(String estado);


    @Query("SELECT c FROM Clube c WHERE c.estado = ?1")
    List<Clube> findByEstadoUsingJPQL(String estado);


    @Query(value = "SELECT * FROM clube WHERE estado = ?1", nativeQuery = true)
    List<Clube> findByEstadoUsingNative(String estado);


}
