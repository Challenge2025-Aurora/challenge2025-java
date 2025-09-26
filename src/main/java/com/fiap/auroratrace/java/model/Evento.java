package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipo;

    private String descricao;

    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "moto_id", nullable = false)
    private Moto moto;

    private Evento(String tipo, String descricao, Long motoId) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo do evento é obrigatório");
        }

        this.tipo = tipo;
        this.descricao = descricao;
        this.criadoEm = LocalDateTime.now();
    }

    public static Evento create(String tipo, String descricao, Long motoId) {
        return new Evento(tipo, descricao, motoId);
    }
}
