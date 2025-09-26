package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deteccao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modeloProb;
    private double confianca;

    private int bboxX;
    private int bboxY;
    private int bboxW;
    private int bboxH;

    private String setorEstimado;
    private String slotEstimado;

    private LocalDateTime timestamp;

    private Deteccao(String placa, String modeloProb, double confianca,
                     int bboxX, int bboxY, int bboxW, int bboxH,
                     String setorEstimado, String slotEstimado) {
        if (confianca < 0 || confianca > 1) {
            throw new IllegalArgumentException("Confiança deve estar entre 0 e 1");
        }

        this.placa = placa != null ? placa.toUpperCase() : null;
        this.modeloProb = modeloProb;
        this.confianca = confianca;
        this.bboxX = bboxX;
        this.bboxY = bboxY;
        this.bboxW = bboxW;
        this.bboxH = bboxH;
        this.setorEstimado = setorEstimado;
        this.slotEstimado = slotEstimado;
        this.timestamp = LocalDateTime.now();
    }

    public static Deteccao create(String placa, String modeloProb, double confianca,
                                  int bboxX, int bboxY, int bboxW, int bboxH,
                                  String setorEstimado, String slotEstimado) {
        return new Deteccao(placa, modeloProb, confianca, bboxX, bboxY, bboxW, bboxH, setorEstimado, slotEstimado);
    }
}
