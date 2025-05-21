package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String caminhoArquivo;

    private LocalDateTime capturadaEm;

    @ManyToOne(optional = false)
    private Camera camera;

    @ManyToOne
    private Moto moto;

    protected Imagem() {}

    public Imagem(String caminhoArquivo, Camera camera, Moto moto) {
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) throw new RuntimeException("Caminho da imagem é obrigatório");
        this.caminhoArquivo = caminhoArquivo;
        this.camera = camera;
        this.moto = moto;
        this.capturadaEm = LocalDateTime.now();
    }
}
