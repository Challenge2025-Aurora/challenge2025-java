package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.model.Moto;
import com.fiap.auroratrace.java.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @GetMapping
    public PagedModel<EntityModel<Moto>> listar(Pageable pageable) {
        Page<Moto> page = service.listar(pageable);
        return PagedModel.of(
                page.map(m -> EntityModel.of(m,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MotoController.class).buscar(m.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Moto> buscar(@PathVariable Integer id) {
        Moto moto = service.buscarPorId(id);
        return EntityModel.of(moto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MotoController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MotoController.class).listar(Pageable.unpaged())).withRel("todas"));
    }

    @PostMapping
    public ResponseEntity<Moto> criar(@RequestBody @Valid MotoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
