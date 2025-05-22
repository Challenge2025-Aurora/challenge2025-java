package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class ImagemDTO {
    @Size(max = 15)
    private String origemImg;

    @NotNull
    private Integer cameraId;

    @NotNull
    private Integer funcionarioId;

    public String getOrigemImg() { return origemImg; }
    public void setOrigemImg(String origemImg) { this.origemImg = origemImg; }

    public Integer getCameraId() { return cameraId; }
    public void setCameraId(Integer cameraId) { this.cameraId = cameraId; }

    public Integer getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Integer funcionarioId) { this.funcionarioId = funcionarioId; }
}
