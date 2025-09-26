package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.EventoDTO;
import com.fiap.auroratrace.java.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Registro de eventos e histórico de motos")
public class EventoController {

    private final EventoService eventoService;
    private final PagedResourcesAssembler<EventoDTO> pagedResourcesAssembler;

    @Autowired
    public EventoController(EventoService eventoService, PagedResourcesAssembler<EventoDTO> pagedResourcesAssembler) {
        this.eventoService = eventoService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Operation(summary = "Lista todos os eventos com paginação")
    @GetMapping
    public PagedModel<EntityModel<EventoDTO>> listarTodos(Pageable pageable) {
        Page<EventoDTO> eventos = eventoService.listarTodos(pageable);
        return pagedResourcesAssembler.toModel(eventos, evento -> {
            EntityModel<EventoDTO> resource = EntityModel.of(evento);
            resource.add(linkTo(methodOn(EventoController.class).buscarPorId(evento.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca um evento pelo ID")
    @GetMapping("/{id}")
    public EntityModel<EventoDTO> buscarPorId(@PathVariable Long id) {
        EventoDTO evento = eventoService.buscarPorId(id);
        EntityModel<EventoDTO> resource = EntityModel.of(evento);
        resource.add(linkTo(methodOn(EventoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(EventoController.class).listarTodos(Pageable.unpaged())).withRel("todos"));
        return resource;
    }

    @Operation(summary = "Cria um novo evento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<EventoDTO> criar(@RequestBody @Valid EventoDTO eventoDTO) {
        EventoDTO novoEvento = eventoService.criar(eventoDTO);
        return EntityModel.of(novoEvento,
                linkTo(methodOn(EventoController.class).buscarPorId(novoEvento.getId())).withSelfRel(),
                linkTo(methodOn(EventoController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }

    @Operation(summary = "Deleta um evento pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        eventoService.deletar(id);
    }

    @Operation(summary = "Busca eventos por ID da moto")
    @GetMapping("/moto/{motoId}")
    public PagedModel<EntityModel<EventoDTO>> buscarPorMotoId(@PathVariable Long motoId, Pageable pageable) {
        Page<EventoDTO> eventos = eventoService.buscarPorMotoId(motoId, pageable);
        return pagedResourcesAssembler.toModel(eventos, evento -> {
            EntityModel<EventoDTO> resource = EntityModel.of(evento);
            resource.add(linkTo(methodOn(EventoController.class).buscarPorId(evento.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca o último evento de uma moto")
    @GetMapping("/moto/{motoId}/ultimo")
    public EntityModel<EventoDTO> buscarUltimoPorMotoId(@PathVariable Long motoId) {
        EventoDTO evento = eventoService.buscarUltimoPorMotoId(motoId);
        return EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).buscarUltimoPorMotoId(motoId)).withSelfRel()
        );
    }
}