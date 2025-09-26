package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class SetorDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotNull
    @Min(1)
    private Integer slots;

    @NotNull
    @Positive
    private Long patioId;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getSlots() { return slots; }
    public void setSlots(Integer slots) { this.slots = slots; }

    public Long getPatioId() { return patioId; }
    public void setPatioId(Long patioId) { this.patioId = patioId; }
}