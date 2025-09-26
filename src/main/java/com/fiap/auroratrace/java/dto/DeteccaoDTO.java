package com.fiap.auroratrace.java.dto;

import jakarta.validation.constraints.*;

public class DeteccaoDTO {
    @NotBlank
    @Size(max = 10)
    private String placa;

    @Size(max = 50)
    private String modeloProb;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double confianca;

    @NotNull
    @Min(0)
    private Integer bboxX;

    @NotNull
    @Min(0)
    private Integer bboxY;

    @NotNull
    @Min(1)
    private Integer bboxW;

    @NotNull
    @Min(1)
    private Integer bboxH;

    @Size(max = 50)
    private String setorEstimado;

    @Size(max = 10)
    private String slotEstimado;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModeloProb() { return modeloProb; }
    public void setModeloProb(String modeloProb) { this.modeloProb = modeloProb; }

    public Double getConfianca() { return confianca; }
    public void setConfianca(Double confianca) { this.confianca = confianca; }

    public Integer getBboxX() { return bboxX; }
    public void setBboxX(Integer bboxX) { this.bboxX = bboxX; }

    public Integer getBboxY() { return bboxY; }
    public void setBboxY(Integer bboxY) { this.bboxY = bboxY; }

    public Integer getBboxW() { return bboxW; }
    public void setBboxW(Integer bboxW) { this.bboxW = bboxW; }

    public Integer getBboxH() { return bboxH; }
    public void setBboxH(Integer bboxH) { this.bboxH = bboxH; }

    public String getSetorEstimado() { return setorEstimado; }
    public void setSetorEstimado(String setorEstimado) { this.setorEstimado = setorEstimado; }

    public String getSlotEstimado() { return slotEstimado; }
    public void setSlotEstimado(String slotEstimado) { this.slotEstimado = slotEstimado; }
}