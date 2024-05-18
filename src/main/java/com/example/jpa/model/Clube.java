package com.example.jpa.model;

import com.example.jpa.dto.ClubeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "clube")
@Entity
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fundacao")
    private Date fundacao;

    @Column(name = "estadio")
    private String estadio;

    public Clube(ClubeDTO clubeDTO) {
        this.nome = clubeDTO.nome();
        this.cidade = clubeDTO.cidade();
        this.estado = clubeDTO.estado();
        this.fundacao = clubeDTO.fundacao();
        this.estadio = clubeDTO.estadio();
    }

    public Clube() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFundacao() {
        return fundacao;
    }

    public void setFundacao(Date fundacao) {
        this.fundacao = fundacao;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
}
