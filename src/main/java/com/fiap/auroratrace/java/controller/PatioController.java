package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patios")
public class PatioController {

    private final PatioService service;

    public PatioController(PatioService service) {
        this.service = service;
    }

    @GetMapping
    public PagedModel<EntityModel<Patio>> listar(Pageable pageable) {
        Page<Patio> page = service.listar(pageable);
        return PagedModel.of(
                page.map(p -> EntityModel.of(p,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatioController.class).buscar(p.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Patio> buscar(@PathVariable Integer id) {
        Patio patio = service.buscarPorId(id);
        return EntityModel.of(patio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatioController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatioController.class).listar(Pageable.unpaged())).withRel("todos"));
    }

    @PostMapping
    public ResponseEntity<Patio> criar(@RequestBody @Valid PatioDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
