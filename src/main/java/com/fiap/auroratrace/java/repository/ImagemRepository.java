package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {}
