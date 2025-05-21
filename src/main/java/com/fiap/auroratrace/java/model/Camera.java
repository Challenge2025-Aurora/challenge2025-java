package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String posicao;

    @ManyToOne(optional = false)
    private Patio patio;

    private LocalDateTime instaladaEm;

    protected Camera() {}

    public Camera(String nome, String posicao, Patio patio) {
        if (nome == null || nome.isBlank()) throw new RuntimeException("Nome da câmera é obrigatório");
        if (posicao == null || posicao.isBlank()) throw new RuntimeException("Posição da câmera é obrigatória");
        this.nome = nome;
        this.posicao = posicao;
        this.patio = patio;
        this.instaladaEm = LocalDateTime.now();
    }
}
