package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Integer> {
    Patio findByNomePatio(String nomePatio);
}