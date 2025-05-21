package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class LocalizacaoDTO {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
