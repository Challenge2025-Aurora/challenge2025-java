package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Patio {
    @Id
    private Integer id;

    @Size(max = 50)
    private String nomePatio;

    @NotBlank
    @Size(max = 100)
    private String endPatio;

    protected Patio() {}

    public Patio(String nomePatio, String endPatio) {
        this.nomePatio = nomePatio;
        this.endPatio = endPatio;
    }
}
