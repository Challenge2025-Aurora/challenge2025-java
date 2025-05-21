package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {}
