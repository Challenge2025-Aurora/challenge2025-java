package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.LocalizacaoDTO;
import com.fiap.auroratrace.java.model.Localizacao;
import com.fiap.auroratrace.java.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas as localizações")
    public PagedModel<EntityModel<Localizacao>> listar(Pageable pageable) {
        Page<Localizacao> page = service.listar(pageable);
        return PagedModel.of(
                page.map(l -> EntityModel.of(l,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).buscar(l.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar localização por ID")
    public EntityModel<Localizacao> buscar(@PathVariable Integer id) {
        Localizacao l = service.buscarPorId(id);
        return EntityModel.of(l,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalizacaoController.class).listar(Pageable.unpaged())).withRel("todas"));
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova localização")
    public ResponseEntity<Localizacao> criar(@RequestBody @Valid LocalizacaoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar localização por ID")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
