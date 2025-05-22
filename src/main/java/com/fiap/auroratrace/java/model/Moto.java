package com.fiap.auroratrace.java.model;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Entity
public class Moto {
    @Id
    private Integer id;

    @NotBlank
    private String modelo;

    @NotBlank
    @Size(max = 15)
    private String placa;

    @Size(max = 20)
    private String cor;

    @Size(max = 20)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "localizacao_id_loc")
    private Localizacao localizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patio_id_patio")
    private Patio patio;

    protected Moto() {}

    public Moto(String modelo, String placa, String cor, String status, Localizacao localizacao, Patio patio) {
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.status = status;
        this.localizacao = localizacao;
        this.patio = patio;
    }
}
