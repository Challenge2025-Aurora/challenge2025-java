package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.DeteccaoDTO;
import com.fiap.auroratrace.java.model.Deteccao;
import com.fiap.auroratrace.java.repository.DeteccaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeteccaoService {

    private final DeteccaoRepository deteccaoRepository;

    @Autowired
    public DeteccaoService(DeteccaoRepository deteccaoRepository) {
        this.deteccaoRepository = deteccaoRepository;
    }

    private DeteccaoDTO toDTO(Deteccao deteccao) {
        DeteccaoDTO dto = new DeteccaoDTO();
        dto.setPlaca(deteccao.getPlaca());
        dto.setModeloProb(deteccao.getModeloProb());
        dto.setConfianca(deteccao.getConfianca());
        dto.setBboxX(deteccao.getBboxX());
        dto.setBboxY(deteccao.getBboxY());
        dto.setBboxW(deteccao.getBboxW());
        dto.setBboxH(deteccao.getBboxH());
        dto.setSetorEstimado(deteccao.getSetorEstimado());
        dto.setSlotEstimado(deteccao.getSlotEstimado());
        return dto;
    }

    private Deteccao toEntity(DeteccaoDTO dto) {
        return Deteccao.create(
                dto.getPlaca(),
                dto.getModeloProb(),
                dto.getConfianca(),
                dto.getBboxX(),
                dto.getBboxY(),
                dto.getBboxW(),
                dto.getBboxH(),
                dto.getSetorEstimado(),
                dto.getSlotEstimado()
        );
    }

    public Page<DeteccaoDTO> listarTodas(Pageable pageable) {
        return deteccaoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public DeteccaoDTO buscarPorId(Long id) {
        Deteccao deteccao = deteccaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detecção não encontrada com ID: " + id));
        return toDTO(deteccao);
    }

    public DeteccaoDTO criar(DeteccaoDTO deteccaoDTO) {
        Deteccao novaDeteccao = toEntity(deteccaoDTO);
        Deteccao deteccaoSalva = deteccaoRepository.save(novaDeteccao);
        return toDTO(deteccaoSalva);
    }

    public void deletar(Long id) {
        if (!deteccaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detecção não encontrada com ID: " + id);
        }
        deteccaoRepository.deleteById(id);
    }

    public Page<DeteccaoDTO> buscarPorPlaca(String placa, Pageable pageable) {
        return deteccaoRepository.findByPlaca(placa.toUpperCase(), pageable)
                .map(this::toDTO);
    }

    public Page<DeteccaoDTO> buscarPorSetor(String setorEstimado, Pageable pageable) {
        return deteccaoRepository.findBySetorEstimado(setorEstimado, pageable)
                .map(this::toDTO);
    }

    public DeteccaoDTO buscarUltimaPorPlaca(String placa) {
        Deteccao ultimaDeteccao = deteccaoRepository.findTopByPlacaOrderByTimestampDesc(placa.toUpperCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma detecção encontrada para a placa: " + placa));
        return toDTO(ultimaDeteccao);
    }
}