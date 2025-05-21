package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.LocalizacaoDTO;
import com.fiap.auroratrace.java.model.Localizacao;
import com.fiap.auroratrace.java.repository.LocalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public List<Localizacao> listar() {
        return repository.findAll();
    }

    public Localizacao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));
    }

    public Localizacao criar(LocalizacaoDTO dto) {
        Localizacao localizacao = new Localizacao(dto.getLatitude(), dto.getLongitude());
        return repository.save(localizacao);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Localização não encontrada");
        }
        repository.deleteById(id);
    }
}
