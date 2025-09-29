package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.model.Moto;
import com.fiap.auroratrace.java.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    @Autowired
    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    private MotoDTO toDTO(Moto moto) {
        MotoDTO dto = new MotoDTO();
        dto.setPlaca(moto.getPlaca());
        dto.setModelo(moto.getModelo());
        dto.setStatus(moto.getStatus());
        dto.setUltimoSetor(moto.getUltimoSetor());
        dto.setUltimoSlot(moto.getUltimoSlot());
        dto.setAtualizadoEm(moto.getAtualizadoEm());
        dto.setId(moto.getId());
        return dto;
    }

    private Moto toEntity(MotoDTO dto) {
        return Moto.builder()
                .placa(dto.getPlaca())
                .modelo(dto.getModelo())
                .status(dto.getStatus())
                .ultimoSetor(dto.getUltimoSetor())
                .ultimoSlot(dto.getUltimoSlot())
                .build();
    }

    public Page<MotoDTO> listarTodas(Pageable pageable) {
        return motoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public MotoDTO buscarPorId(Long id) {
        Moto moto = motoRepository.findById(id.intValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com ID: " + id));
        return toDTO(moto);
    }

    public MotoDTO criar(MotoDTO motoDTO) {
        Moto novaMoto = toEntity(motoDTO);
        Moto motoSalva = motoRepository.save(novaMoto);
        return toDTO(motoSalva);
    }

    // ...
    public MotoDTO atualizar(Long id, MotoDTO motoDTO) {
        Moto motoExistente = motoRepository.findById(id.intValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com ID: " + id));

        motoExistente.setPlaca(motoDTO.getPlaca().toUpperCase());
        motoExistente.setModelo(motoDTO.getModelo());

        motoExistente.atualizarStatus(
                motoDTO.getStatus(),
                motoDTO.getUltimoSetor(),
                motoDTO.getUltimoSlot()
        );

        Moto motoAtualizada = motoRepository.save(motoExistente);
        return toDTO(motoAtualizada);
    }

    public void deletar(Long id) {
        if (!motoRepository.existsById(id.intValue())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com ID: " + id);
        }
        motoRepository.deleteById(id.intValue());
    }

    public Page<MotoDTO> buscarPorStatus(String status, Pageable pageable) {
        return motoRepository.findByStatus(status, pageable)
                .map(this::toDTO);
    }

    public Page<MotoDTO> buscarPorPlaca(String placa, Pageable pageable) {
        return motoRepository.findByPlacaContainingIgnoreCase(placa, pageable)
                .map(this::toDTO);
    }
}