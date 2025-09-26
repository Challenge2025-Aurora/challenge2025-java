package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class EventoDTO {
    @NotBlank
    @Size(max = 50)
    private String tipo;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @Positive
    private Long motoId;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }
}