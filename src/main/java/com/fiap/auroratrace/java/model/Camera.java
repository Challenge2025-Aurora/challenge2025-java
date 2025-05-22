package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Camera {
    @Id
    private Integer id;

    @Size(max = 50)
    private String nomeCam;

    @Size(max = 30)
    private String localizacaoCam;

    @NotBlank
    @Size(max = 200)
    private String ipCam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio")
    private Patio patio;

    protected Camera() {}

    public Camera(String nomeCam, String localizacaoCam, String ipCam, Patio patio) {
        this.nomeCam = nomeCam;
        this.localizacaoCam = localizacaoCam;
        this.ipCam = ipCam;
        this.patio = patio;
    }
}
