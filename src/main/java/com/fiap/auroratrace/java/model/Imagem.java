package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 15)
    private String origemImg;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraImg;

    @ManyToOne(optional = false)
    @JoinColumn(name = "camera_id_cam")
    private Camera camera;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id_func")
    private Funcionario funcionario;
}