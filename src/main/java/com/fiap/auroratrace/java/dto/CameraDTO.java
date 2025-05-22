package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class CameraDTO {
    @Size(max = 50)
    private String nomeCam;

    @Size(max = 30)
    private String localizacaoCam;

    @NotBlank
    @Size(max = 200)
    private String ipCam;

    @NotNull
    private Integer patioId;

    public String getNomeCam() { return nomeCam; }
    public void setNomeCam(String nomeCam) { this.nomeCam = nomeCam; }

    public String getLocalizacaoCam() { return localizacaoCam; }
    public void setLocalizacaoCam(String localizacaoCam) { this.localizacaoCam = localizacaoCam; }

    public String getIpCam() { return ipCam; }
    public void setIpCam(String ipCam) { this.ipCam = ipCam; }

    public Integer getPatioId() { return patioId; }
    public void setPatioId(Integer patioId) { this.patioId = patioId; }
}
