package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String modelo;

    @NotBlank
    @Size(max = 15)
    private String placa;

    @Size(max = 20)
    private String cor;

    @Size(max = 20)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "localizacao_id_loc")
    private Localizacao localizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio")
    private Patio patio;
}