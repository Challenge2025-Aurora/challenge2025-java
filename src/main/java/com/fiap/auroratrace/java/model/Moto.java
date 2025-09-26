package com.fiap.auroratrace.java.model;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private LocalDateTime atualizadoEm;

    private String ultimoSetor;

    private String ultimoSlot;

    private Moto(String placa, String modelo, StatusMoto status, String ultimoSetor, String ultimoSlot) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa é obrigatória");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo é obrigatório");
        }

        this.placa = placa.toUpperCase();
        this.modelo = modelo;
        this.status = status;
        this.atualizadoEm = LocalDateTime.now();
        this.ultimoSetor = ultimoSetor;
        this.ultimoSlot = ultimoSlot;
    }

    public static Moto create(String placa, String modelo, StatusMoto status, String ultimoSetor, String ultimoSlot) {
        return new Moto(placa, modelo, status, ultimoSetor, ultimoSlot);
    }

    public void atualizarStatus(StatusMoto novoStatus, String ultimoSetor, String ultimoSlot) {
        this.status = novoStatus;
        this.ultimoSetor = ultimoSetor;
        this.ultimoSlot = ultimoSlot;
        this.atualizadoEm = LocalDateTime.now();
    }
}
