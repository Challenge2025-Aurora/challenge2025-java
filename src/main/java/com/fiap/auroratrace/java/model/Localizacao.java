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
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
