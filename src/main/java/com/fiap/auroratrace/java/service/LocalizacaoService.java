package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.LocalizacaoDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Localizacao;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.LocalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;
    private final CameraRepository cameraRepository;

    public LocalizacaoService(LocalizacaoRepository repository, CameraRepository cameraRepository) {
        this.repository = repository;
        this.cameraRepository = cameraRepository;
    }

    public List<Localizacao> listar() {
        return repository.findAll();
    }

    public Localizacao buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));
    }

    public Localizacao criar(LocalizacaoDTO dto) {
        Camera camera = cameraRepository.findById(dto.getCameraId())
                .orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));

        Localizacao localizacao = new Localizacao(
                dto.getPosicaoLoc(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getOrigemLoc(),
                new Date(),
                camera
        );

        return repository.save(localizacao);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Localização não encontrada");
        }
        repository.deleteById(id);
    }
}
