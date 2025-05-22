package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatioService {

    private final PatioRepository repository;

    public PatioService(PatioRepository repository) {
        this.repository = repository;
    }

    public Page<Patio> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Patio buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
    }

    public Patio criar(PatioDTO dto) {
        Patio patio = Patio.builder()
                .nomePatio(dto.getNomePatio())
                .endPatio(dto.getEndPatio())
                .build();
        return repository.save(patio);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Pátio não encontrado");
        }
        repository.deleteById(id);
    }
}
