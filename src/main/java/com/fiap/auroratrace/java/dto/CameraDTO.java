package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class CameraDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String posicao;

    @NotNull
    private Long patioId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public Long getPatioId() { return patioId; }
    public void setPatioId(Long patioId) { this.patioId = patioId; }
}
