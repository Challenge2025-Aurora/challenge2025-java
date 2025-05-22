package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
    List<Localizacao> findByCameraId(Integer cameraId);
    List<Localizacao> findByOrigemLoc(String origemLoc);
}
