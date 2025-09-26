package com.fiap.auroratrace.java.dto;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import jakarta.validation.constraints.*;

public class MotoDTO {
    @NotBlank
    @Size(max = 10)
    private String placa;

    @NotBlank
    @Size(max = 50)
    private String modelo;

    @NotNull
    private StatusMoto status;

    @Size(max = 50)
    private String ultimoSetor;

    @Size(max = 10)
    private String ultimoSlot;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public StatusMoto getStatus() { return status; }
    public void setStatus(StatusMoto status) { this.status = status; }

    public String getUltimoSetor() { return ultimoSetor; }
    public void setUltimoSetor(String ultimoSetor) { this.ultimoSetor = ultimoSetor; }

    public String getUltimoSlot() { return ultimoSlot; }
    public void setUltimoSlot(String ultimoSlot) { this.ultimoSlot = ultimoSlot; }
}