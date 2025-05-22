package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.CameraDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.service.CameraService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    private final CameraService service;

    public CameraController(CameraService service) {
        this.service = service;
    }

    @GetMapping
    public PagedModel<EntityModel<Camera>> listar(Pageable pageable) {
        Page<Camera> page = service.listar(pageable);
        return PagedModel.of(
                page.map(camera -> EntityModel.of(camera,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CameraController.class).buscar(camera.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Camera> buscar(@PathVariable Integer id) {
        Camera camera = service.buscarPorId(id);
        return EntityModel.of(camera,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CameraController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CameraController.class).listar(Pageable.unpaged())).withRel("todas"));
    }

    @PostMapping
    public ResponseEntity<Camera> criar(@RequestBody @Valid CameraDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}