package com.fiap.auroratrace.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String nomeFunc;

    @Size(max = 100)
    private String emailFunc;

    @Size(max = 20)
    private String cargo;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpfFunc;
}