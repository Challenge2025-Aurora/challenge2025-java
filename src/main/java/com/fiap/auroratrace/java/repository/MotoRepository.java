package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {}
