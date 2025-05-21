package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String matricula;

    @NotBlank
    private String cargo;

    private String telefone;

    protected Funcionario() {}

    public Funcionario(String nome, String matricula, String cargo, String telefone) {
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.telefone = telefone;
    }
}
