package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.ImagemDTO;
import com.fiap.auroratrace.java.model.Imagem;
import com.fiap.auroratrace.java.service.ImagemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemService service;

    public ImagemController(ImagemService service) {
        this.service = service;
    }

    @GetMapping
    public PagedModel<EntityModel<Imagem>> listar(Pageable pageable) {
        Page<Imagem> page = service.listar(pageable);
        return PagedModel.of(
                page.map(i -> EntityModel.of(i,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImagemController.class).buscar(i.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Imagem> buscar(@PathVariable Integer id) {
        Imagem imagem = service.buscarPorId(id);
        return EntityModel.of(imagem,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImagemController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImagemController.class).listar(Pageable.unpaged())).withRel("todas"));
    }

    @PostMapping
    public ResponseEntity<Imagem> criar(@RequestBody @Valid ImagemDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
