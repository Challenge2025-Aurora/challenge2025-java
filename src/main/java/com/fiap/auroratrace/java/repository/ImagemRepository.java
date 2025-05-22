package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    List<Imagem> findByCameraId(Integer cameraId);
    List<Imagem> findByFuncionarioId(Integer funcionarioId);
    List<Imagem> findByOrigemImg(String origemImg);
}
