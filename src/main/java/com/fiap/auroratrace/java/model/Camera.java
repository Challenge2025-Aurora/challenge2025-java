package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
