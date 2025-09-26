package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private int slots;

    @ManyToOne
    @JoinColumn(name = "patio_id", nullable = false)
    private Patio patio;

    private Setor(String nome, int slots, Patio patio) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do setor é obrigatório");
        }
        if (slots <= 0) {
            throw new IllegalArgumentException("Quantidade de slots deve ser positiva");
        }
        if (patio == null) {
            throw new IllegalArgumentException("Patio é obrigatório");
        }

        this.nome = nome;
        this.slots = slots;
        this.patio = patio;
    }

    public static Setor create(String nome, int slots, Patio patio) {
        return new Setor(nome, slots, patio);
    }
}
