package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.SetorDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.model.Setor;
import com.fiap.auroratrace.java.repository.PatioRepository;
import com.fiap.auroratrace.java.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    private final SetorRepository setorRepository;
    private final PatioRepository patioRepository;

    @Autowired
    public SetorService(SetorRepository setorRepository, PatioRepository patioRepository) {
        this.setorRepository = setorRepository;
        this.patioRepository = patioRepository;
    }

    private SetorDTO toDTO(Setor setor) {
        SetorDTO dto = new SetorDTO();
        dto.setNome(setor.getNome());
        dto.setSlots(setor.getSlots());
        dto.setPatioId(setor.getPatio().getId());
        return dto;
    }

    private Setor toEntity(SetorDTO dto, Patio patio) {
        return Setor.create(
                dto.getNome(),
                dto.getSlots(),
                patio
        );
    }

    public Page<SetorDTO> listarTodos(Pageable pageable) {
        return setorRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public SetorDTO buscarPorId(Long id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com ID: " + id));
        return toDTO(setor);
    }

    public SetorDTO criar(SetorDTO setorDTO) {
        Patio patio = patioRepository.findById(setorDTO.getPatioId().intValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pátio não encontrado com ID: " + setorDTO.getPatioId()));

        if (setorRepository.existsByNomeAndPatioId(setorDTO.getNome(), setorDTO.getPatioId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um Setor com este nome neste Pátio.");
        }

        Setor novoSetor = toEntity(setorDTO, patio);
        Setor setorSalvo = setorRepository.save(novoSetor);

        return toDTO(setorSalvo);
    }

    public SetorDTO atualizar(Long id, SetorDTO setorDTO) {
        Setor setorExistente = setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com ID: " + id));

        if (!setorExistente.getPatio().getId().equals(setorDTO.getPatioId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é permitido alterar o Pátio de um Setor existente.");
        }

        if (!setorExistente.getNome().equals(setorDTO.getNome()) &&
                setorRepository.existsByNomeAndPatioId(setorDTO.getNome(), setorDTO.getPatioId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe outro Setor com este nome neste Pátio.");
        }

        setorExistente.setNome(setorDTO.getNome());
        setorExistente.setSlots(setorDTO.getSlots());

        Setor setorAtualizado = setorRepository.save(setorExistente);

        return toDTO(setorAtualizado);
    }

    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com ID: " + id);
        }
        setorRepository.deleteById(id);
    }

    public Page<SetorDTO> buscarPorPatioId(Long patioId, Pageable pageable) {
        return setorRepository.findByPatioId(patioId, pageable)
                .map(this::toDTO);
    }

    public SetorDTO buscarPorNome(String nome) {
        Setor setor = setorRepository.findByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com nome: " + nome));
        return toDTO(setor);
    }
}