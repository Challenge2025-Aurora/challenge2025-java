package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByTipo(String tipo, Pageable pageable);

    Page<Evento> findByMotoId(Long motoId, Pageable pageable);

    Page<Evento> findByTipoAndCriadoEmBetween(String tipo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    boolean existsByTipoAndMotoId(String tipo, Long motoId);
    Optional<Evento> findTopByMotoIdOrderByCriadoEmDesc(Long motoId);

    @Transactional
    void deleteAllByMotoId(Long motoId);
}