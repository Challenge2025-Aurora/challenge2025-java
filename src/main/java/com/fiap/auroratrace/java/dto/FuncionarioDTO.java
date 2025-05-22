package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class FuncionarioDTO {
    @NotBlank
    @Size(max = 50)
    private String nomeFunc;

    @Size(max = 100)
    private String emailFunc;

    @Size(max = 20)
    private String cargo;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpfFunc;

    public String getNomeFunc() { return nomeFunc; }
    public void setNomeFunc(String nomeFunc) { this.nomeFunc = nomeFunc; }

    public String getEmailFunc() { return emailFunc; }
    public void setEmailFunc(String emailFunc) { this.emailFunc = emailFunc; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getCpfFunc() { return cpfFunc; }
    public void setCpfFunc(String cpfFunc) { this.cpfFunc = cpfFunc; }
}
