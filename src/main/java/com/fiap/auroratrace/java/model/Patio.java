package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cidade;

    private double tamanhoMetros;

    protected Patio() {}

    public Patio(String nome, String endereco, String cidade, double tamanhoMetros) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.tamanhoMetros = tamanhoMetros;
    }
}
