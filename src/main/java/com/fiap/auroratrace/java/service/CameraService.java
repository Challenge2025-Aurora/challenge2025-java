package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.CameraDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CameraService {

    private final CameraRepository repository;
    private final PatioRepository patioRepository;

    public CameraService(CameraRepository repository, PatioRepository patioRepository) {
        this.repository = repository;
        this.patioRepository = patioRepository;
    }

    public Page<Camera> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Camera buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));
    }

    public Camera criar(CameraDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        Camera camera = Camera.builder()
                .nomeCam(dto.getNomeCam())
                .localizacaoCam(dto.getLocalizacaoCam())
                .ipCam(dto.getIpCam())
                .patio(patio)
                .build();
        return repository.save(camera);
    }


    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Câmera não encontrada");
        }
        repository.deleteById(id);
    }
}
