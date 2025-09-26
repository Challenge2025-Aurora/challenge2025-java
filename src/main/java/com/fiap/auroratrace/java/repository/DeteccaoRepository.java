package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Deteccao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeteccaoRepository extends JpaRepository<Deteccao, Long> {

    Page<Deteccao> findByPlaca(String placa, Pageable pageable);

    Page<Deteccao> findBySetorEstimado(String setorEstimado, Pageable pageable);

    Page<Deteccao> findByModeloProb(String modeloProb, Pageable pageable);

    Page<Deteccao> findByConfiancaGreaterThan(double confianca, Pageable pageable);

    List<Deteccao> findByBboxXBetweenAndBboxYBetween(int bboxXStart, int bboxXEnd, int bboxYStart, int bboxYEnd);
    List<Deteccao> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    Optional<Deteccao> findTopByPlacaOrderByTimestampDesc(String placa);
}