package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class ImagemDTO {
    @NotBlank
    private String caminhoArquivo;

    @NotNull
    private Long cameraId;

    private Long motoId;

    public String getCaminhoArquivo() { return caminhoArquivo; }
    public void setCaminhoArquivo(String caminhoArquivo) { this.caminhoArquivo = caminhoArquivo; }

    public Long getCameraId() { return cameraId; }
    public void setCameraId(Long cameraId) { this.cameraId = cameraId; }

    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }
}
