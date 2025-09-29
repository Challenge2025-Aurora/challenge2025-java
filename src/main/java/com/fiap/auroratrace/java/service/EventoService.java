package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.EventoDTO;
import com.fiap.auroratrace.java.model.Evento;
import com.fiap.auroratrace.java.model.Moto;
import com.fiap.auroratrace.java.repository.EventoRepository;
import com.fiap.auroratrace.java.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final MotoRepository motoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, MotoRepository motoRepository) {
        this.eventoRepository = eventoRepository;
        this.motoRepository = motoRepository;
    }

    private EventoDTO toDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTipo(evento.getTipo());
        dto.setDescricao(evento.getDescricao());
        dto.setCriadoEm(evento.getCriadoEm());
        dto.setMotoId(evento.getMoto().getId());
        dto.setMotoPlaca(evento.getMoto().getPlaca());
        return dto;
    }

    private Evento toEntity(EventoDTO dto, Moto moto) {
        return Evento.builder()
                .tipo(dto.getTipo())
                .descricao(dto.getDescricao())
                .moto(moto)
                .build();
    }

    public Page<EventoDTO> listarTodos(Pageable pageable) {
        return eventoRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public EventoDTO buscarPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado com ID: " + id));
        return toDTO(evento);
    }

    public EventoDTO criar(EventoDTO eventoDTO) {
        Moto moto = motoRepository.findById(eventoDTO.getMotoId().intValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com ID: " + eventoDTO.getMotoId()));

        Evento novoEvento = toEntity(eventoDTO, moto);
        Evento eventoSalvo = eventoRepository.save(novoEvento);

        return toDTO(eventoSalvo);
    }

    public void deletar(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado com ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    public Page<EventoDTO> buscarPorMotoId(Long motoId, Pageable pageable) {
        return eventoRepository.findByMotoId(motoId, pageable)
                .map(this::toDTO);
    }

    public Page<EventoDTO> buscarPorTipo(String tipo, Pageable pageable) {
        return eventoRepository.findByTipo(tipo, pageable)
                .map(this::toDTO);
    }

    public EventoDTO buscarUltimoPorMotoId(Long motoId) {
        Evento evento = eventoRepository.findTopByMotoIdOrderByCriadoEmDesc(motoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum evento encontrado para a Moto ID: " + motoId));
        return toDTO(evento);
    }
}