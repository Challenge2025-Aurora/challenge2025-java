package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class EventoDTO {
    @NotBlank
    @Size(max = 50)
    private String tipo;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @Positive
    private Long motoId;

    private String motoPlaca;
    private LocalDateTime criadoEm;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }

    public String getMotoPlaca() { return motoPlaca; }
    public void setMotoPlaca(String motoPlaca) { this.motoPlaca = motoPlaca; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}