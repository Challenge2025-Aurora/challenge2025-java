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

    public MotoService(MotoRepository motoRepository, PatioRepository patioRepository,
                       LocalizacaoRepository localizacaoRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<Moto> listar() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Integer id) {
        return motoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
    }

    public Moto criar(MotoDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));

        Moto moto = new Moto(dto.getModelo(), dto.getPlaca(), dto.getCor(), dto.getStatus(), localizacao, patio);
        return motoRepository.save(moto);
    }

    public void deletar(Integer id) {
        if (!motoRepository.existsById(id)) {
            throw new EntityNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
}
