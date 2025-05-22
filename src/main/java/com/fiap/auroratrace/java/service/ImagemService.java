package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.ImagemDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Funcionario;
import com.fiap.auroratrace.java.model.Imagem;
import com.fiap.auroratrace.java.repository.CameraRepository;
import com.fiap.auroratrace.java.repository.FuncionarioRepository;
import com.fiap.auroratrace.java.repository.ImagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ImagemService {

    private final ImagemRepository imagemRepository;
    private final CameraRepository cameraRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ImagemService(ImagemRepository imagemRepository, CameraRepository cameraRepository, FuncionarioRepository funcionarioRepository) {
        this.imagemRepository = imagemRepository;
        this.cameraRepository = cameraRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<Imagem> listar(Pageable pageable) {
        return imagemRepository.findAll(pageable);
    }

    public Imagem buscarPorId(Integer id) {
        return imagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada"));
    }

    public Imagem criar(ImagemDTO dto) {
        Camera camera = cameraRepository.findById(dto.getCameraId())
                .orElseThrow(() -> new EntityNotFoundException("Câmera não encontrada"));
        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        Imagem imagem = Imagem.builder()
                .origemImg(dto.getOrigemImg())
                .datahoraImg(new Date())
                .camera(camera)
                .funcionario(funcionario)
                .build();
        return imagemRepository.save(imagem);
    }

    public void deletar(Integer id) {
        if (!imagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Imagem não encontrada");
        }
        imagemRepository.deleteById(id);
    }
}
