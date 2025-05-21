package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    private final PatioRepository repository;

    public PatioService(PatioRepository repository) {
        this.repository = repository;
    }

    public List<Patio> listar() {
        return repository.findAll();
    }

    public Patio buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
    }

    public Patio criar(PatioDTO dto) {
        Patio patio = new Patio(dto.getNome(), dto.getEndereco(), dto.getCidade(), dto.getTamanhoMetros());
        return repository.save(patio);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Pátio não encontrado");
        }
        repository.deleteById(id);
    }
}
