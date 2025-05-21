package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.ImagemDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Imagem;
import com.fiap.auroratrace.java.model.Moto;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.ImagemRepository;
import com.fiap.auroratrace.java.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final CameraRepository cameraRepository;
    private final MotoRepository motoRepository;

    public ImagemService(ImagemRepository imagemRepository, CameraRepository cameraRepository, MotoRepository motoRepository) {
        this.imagemRepository = imagemRepository;
        this.cameraRepository = cameraRepository;
        this.motoRepository = motoRepository;
    }

    public List<Imagem> listar() {
        return imagemRepository.findAll();
    }

    public Imagem buscarPorId(Long id) {
        return imagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada"));
    }

    public Imagem criar(ImagemDTO dto) {
        Camera camera = cameraRepository.findById(dto.getCameraId())
                .orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));
        Moto moto = dto.getMotoId() != null ? motoRepository.findById(dto.getMotoId()).orElse(null) : null;

        Imagem imagem = new Imagem(dto.getCaminhoArquivo(), camera, moto);
        return imagemRepository.save(imagem);
    }

    public void deletar(Long id) {
        if (!imagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Imagem não encontrada");
        }
        imagemRepository.deleteById(id);
    }
}
