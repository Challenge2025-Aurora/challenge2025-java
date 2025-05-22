package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Funcionario {
    @Id
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String nomeFunc;

    @Size(max = 100)
    private String emailFunc;

    @Size(max = 20)
    private String cargo;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpfFunc;

    protected Funcionario() {}

    public Funcionario(String nomeFunc, String emailFunc, String cargo, String cpfFunc) {
        this.nomeFunc = nomeFunc;
        this.emailFunc = emailFunc;
        this.cargo = cargo;
        this.cpfFunc = cpfFunc;
    }
}
