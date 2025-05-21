package com.fiap.auroratrace.java.dto;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import jakarta.validation.constraints.*;

public class MotoDTO {
    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    @NotNull
    private StatusMoto status;

    @NotNull
    private Long patioId;

    @NotNull
    private Long localizacaoId;

    private Long funcionarioId;

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public StatusMoto getStatus() { return status; }
    public void setStatus(StatusMoto status) { this.status = status; }

    public Long getPatioId() { return patioId; }
    public void setPatioId(Long patioId) { this.patioId = patioId; }

    public Long getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Long localizacaoId) { this.localizacaoId = localizacaoId; }

    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
}
