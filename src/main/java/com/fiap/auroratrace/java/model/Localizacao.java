package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
public class Localizacao {
    @Id
    private Integer id;

    @Size(max = 50)
    private String posicaoLoc;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Size(max = 15)
    private String origemLoc;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraLoc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "camera_id_cam")
    private Camera camera;

    protected Localizacao() {}

    public Localizacao(String posicaoLoc, Double latitude, Double longitude, String origemLoc, Date datahoraLoc, Camera camera) {
        this.posicaoLoc = posicaoLoc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.origemLoc = origemLoc;
        this.datahoraLoc = datahoraLoc;
        this.camera = camera;
    }
}
