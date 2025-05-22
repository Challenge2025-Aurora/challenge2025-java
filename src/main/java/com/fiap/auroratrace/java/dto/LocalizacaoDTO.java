package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class LocalizacaoDTO {
    @Size(max = 50)
    private String posicaoLoc;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Size(max = 15)
    private String origemLoc;

    @NotNull
    private Integer cameraId;

    public String getPosicaoLoc() { return posicaoLoc; }
    public void setPosicaoLoc(String posicaoLoc) { this.posicaoLoc = posicaoLoc; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getOrigemLoc() { return origemLoc; }
    public void setOrigemLoc(String origemLoc) { this.origemLoc = origemLoc; }

    public Integer getCameraId() { return cameraId; }
    public void setCameraId(Integer cameraId) { this.cameraId = cameraId; }
}
