package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Setor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    Page<Setor> findByPatioId(Long patioId, Pageable pageable);

    Optional<Setor> findByNome(String nome);
    boolean existsByNomeAndPatioId(String nome, Long patioId);
    Optional<Setor> findByNomeAndSlots(String nome, int slots);
    List<Setor> findBySlotsGreaterThan(int slots);
}