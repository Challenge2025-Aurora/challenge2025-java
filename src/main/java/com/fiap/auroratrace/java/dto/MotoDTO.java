package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class MotoDTO {
    @NotBlank
    @Size(max = 15)
    private String placa;

    @NotBlank
    @Size(max = 50)
    private String modelo;

    @Size(max = 20)
    private String cor;

    @Size(max = 20)
    private String status;

    @NotNull
    private Integer patioId;

    @NotNull
    private Integer localizacaoId;

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getPatioId() { return patioId; }
    public void setPatioId(Integer patioId) { this.patioId = patioId; }

    public Integer getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Integer localizacaoId) { this.localizacaoId = localizacaoId; }
}