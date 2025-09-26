package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {

    Page<Moto> findByStatus(String status, Pageable pageable);

    Page<Moto> findByPlacaContainingIgnoreCase(String placa, Pageable pageable);
}