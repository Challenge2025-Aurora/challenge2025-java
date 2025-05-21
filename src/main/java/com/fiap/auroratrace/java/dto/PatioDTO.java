package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class PatioDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cidade;

    @NotNull
    private Double tamanhoMetros;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public Double getTamanhoMetros() { return tamanhoMetros; }
    public void setTamanhoMetros(Double tamanhoMetros) { this.tamanhoMetros = tamanhoMetros; }
}
