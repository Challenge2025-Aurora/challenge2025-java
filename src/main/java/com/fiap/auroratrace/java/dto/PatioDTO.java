package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class PatioDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotNull
    @Min(1)
    private Integer cols;

    @NotNull
    @Min(1)
    private Integer rows;

    private Integer id;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getCols() { return cols; }
    public void setCols(Integer cols) { this.cols = cols; }

    public Integer getRows() { return rows; }
    public void setRows(Integer rows) { this.rows = rows; }
}