package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class PatioDTO {
    @Size(max = 50)
    private String nomePatio;

    @NotBlank
    @Size(max = 100)
    private String endPatio;

    public String getNomePatio() { return nomePatio; }
    public void setNomePatio(String nomePatio) { this.nomePatio = nomePatio; }

    public String getEndPatio() { return endPatio; }
    public void setEndPatio(String endPatio) { this.endPatio = endPatio; }
}
