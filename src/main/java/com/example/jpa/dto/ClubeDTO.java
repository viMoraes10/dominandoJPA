package com.example.jpa.dto;

import java.util.Date;

public record ClubeDTO(Long id, String nome, String cidade, String estado, Date fundacao, String estadio) {

}
