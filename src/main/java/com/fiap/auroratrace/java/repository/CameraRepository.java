package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    List<Camera> findByPatioId(Integer patioId);
    List<Camera> findByLocalizacaoCamContainingIgnoreCase(String localizacaoCam);
}
