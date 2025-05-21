package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class FuncionarioDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String matricula;

    @NotBlank
    private String cargo;

    private String telefone;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
