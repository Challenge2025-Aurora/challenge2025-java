package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private LocalDateTime registradaEm;

    protected Localizacao() {}

    public Localizacao(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.registradaEm = LocalDateTime.now();
    }
}
