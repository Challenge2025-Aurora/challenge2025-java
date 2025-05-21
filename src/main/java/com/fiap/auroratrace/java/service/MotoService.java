package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.model.*;
import com.fiap.auroratrace.java.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public MotoService(MotoRepository motoRepository, PatioRepository patioRepository,
                       LocalizacaoRepository localizacaoRepository, FuncionarioRepository funcionarioRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Moto> listar() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
    }

    public Moto criar(MotoDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));
        Funcionario funcionario = dto.getFuncionarioId() != null ?
                funcionarioRepository.findById(dto.getFuncionarioId()).orElse(null) : null;

        Moto moto = new Moto(dto.getPlaca(), dto.getModelo(), dto.getStatus(), patio, localizacao, funcionario);
        return motoRepository.save(moto);
    }

    public void deletar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new EntityNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
}
