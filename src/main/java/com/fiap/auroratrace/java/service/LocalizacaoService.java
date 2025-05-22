package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.LocalizacaoDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Localizacao;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.LocalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;
    private final CameraRepository cameraRepository;

    public LocalizacaoService(LocalizacaoRepository repository, CameraRepository cameraRepository) {
        this.repository = repository;
        this.cameraRepository = cameraRepository;
    }

    public Page<Localizacao> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Localizacao buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));
    }

    public Localizacao criar(LocalizacaoDTO dto) {
        Camera camera = cameraRepository.findById(dto.getCameraId())
                .orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));

        Localizacao localizacao = Localizacao.builder()
                .posicaoLoc(dto.getPosicaoLoc())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .origemLoc(dto.getOrigemLoc())
                .datahoraLoc(new Date())
                .camera(camera)
                .build();
        return repository.save(localizacao);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Localização não encontrada");
        }
        repository.deleteById(id);
    }
}
