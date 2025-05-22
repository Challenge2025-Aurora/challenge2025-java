package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.CameraDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    private final CameraRepository repository;
    private final PatioRepository patioRepository;

    public CameraService(CameraRepository repository, PatioRepository patioRepository) {
        this.repository = repository;
        this.patioRepository = patioRepository;
    }

    public List<Camera> listar() {
        return repository.findAll();
    }

    public Camera buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));
    }

    public Camera criar(CameraDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        Camera camera = new Camera(dto.getNomeCam(), dto.getLocalizacaoCam(), dto.getIpCam(), patio);
        return repository.save(camera);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Câmera não encontrada");
        }
        repository.deleteById(id);
    }
}
