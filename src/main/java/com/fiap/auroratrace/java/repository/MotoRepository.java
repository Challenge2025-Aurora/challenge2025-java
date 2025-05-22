package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findByStatus(String status);
    List<Moto> findByPatioId(Integer patioId);
    List<Moto> findByLocalizacaoId(Integer localizacaoId);
    List<Moto> findByPlacaContainingIgnoreCase(String placa);
}