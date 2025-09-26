package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatioService {

    private final PatioRepository patioRepository;

    @Autowired
    public PatioService(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    private PatioDTO toDTO(Patio patio) {
        PatioDTO dto = new PatioDTO();
        dto.setNome(patio.getNome());
        dto.setCols(patio.getCols());
        dto.setRows(patio.getRows());
        return dto;
    }

    private Patio toEntity(PatioDTO dto) {
        return Patio.create(
                dto.getNome(),
                dto.getCols(),
                dto.getRows()
        );
    }

    public Page<PatioDTO> listarTodos(Pageable pageable) {
        return patioRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public PatioDTO buscarPorId(Integer id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado com ID: " + id));
        return toDTO(patio);
    }

    public PatioDTO criar(PatioDTO patioDTO) {
        Patio existingPatio = patioRepository.findByNome(patioDTO.getNome());
        if (existingPatio != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pátio com este nome já existe.");
        }

        Patio novoPatio = toEntity(patioDTO);
        Patio patioSalvo = patioRepository.save(novoPatio);

        return toDTO(patioSalvo);
    }

    public PatioDTO atualizar(Integer id, PatioDTO patioDTO) {
        Patio patioExistente = patioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado com ID: " + id));

        patioExistente.setNome(patioDTO.getNome());
        patioExistente.setCols(patioDTO.getCols());
        patioExistente.setRows(patioDTO.getRows());

        Patio patioAtualizado = patioRepository.save(patioExistente);

        return toDTO(patioAtualizado);
    }

    public void deletar(Integer id) {
        if (!patioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado com ID: " + id);
        }
        patioRepository.deleteById(id);
    }

    public PatioDTO buscarPorNome(String nome) {
        Patio patio = patioRepository.findByNome(nome);
        if (patio == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio com nome '" + nome + "' não encontrado.");
        }
        return toDTO(patio);
    }
}