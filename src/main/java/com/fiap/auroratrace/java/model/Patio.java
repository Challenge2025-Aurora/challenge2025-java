package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do pátio é obrigatório.")
    @Size(max = 50, message = "O nome do pátio não pode ter mais de 50 caracteres.")
    private String nome;

    @Min(value = 1, message = "O número de colunas deve ser maior que zero.")
    private int cols;

    @Min(value = 1, message = "O número de linhas deve ser maior que zero.")
    private int rows;

    @Builder.Default
    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Setor> setores = new ArrayList<>();

    public List<Setor> getSetores() {
        return Collections.unmodifiableList(setores);
    }

    private Patio(String nome, int cols, int rows) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do pátio é obrigatório");
        }
        if (cols <= 0 || rows <= 0) {
            throw new IllegalArgumentException("Mapa do pátio deve ter dimensões válidas");
        }

        this.nome = nome;
        this.cols = cols;
        this.rows = rows;
    }

    public static Patio create(String nome, int cols, int rows) {
        return new Patio(nome, cols, rows);
    }
}
