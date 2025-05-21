package com.fiap.auroratrace.java.model;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    private LocalDateTime ultimaAtualizacao;

    @ManyToOne(optional = false)
    private Patio patio;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne(optional = false)
    private Localizacao localizacao;

    protected Moto() {}

    public Moto(String placa, String modelo, StatusMoto status, Patio patio, Localizacao localizacao, Funcionario funcionario) {
        validarPlaca(placa);
        this.placa = placa;
        this.modelo = modelo;
        this.status = status;
        this.ultimaAtualizacao = LocalDateTime.now();
        this.patio = patio;
        this.localizacao = localizacao;
        this.funcionario = funcionario;
    }

    private void validarPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) throw new RuntimeException("Placa é obrigatória");
        String regex = "^[A-Z]{3}\\d{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
        if (!Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(placa).matches()) {
            throw new RuntimeException("Placa inválida: " + placa);
        }
    }
}
