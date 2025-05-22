package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
public class Imagem {
    @Id
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

    protected Imagem() {}

    public Imagem(String origemImg, Date datahoraImg, Camera camera, Funcionario funcionario) {
        this.origemImg = origemImg;
        this.datahoraImg = datahoraImg;
        this.camera = camera;
        this.funcionario = funcionario;
    }
}
