package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.FuncionarioDTO;
import com.fiap.auroratrace.java.model.Funcionario;
import com.fiap.auroratrace.java.service.FuncionarioService;
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
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos os funcionários")
    public PagedModel<EntityModel<Funcionario>> listar(Pageable pageable) {
        Page<Funcionario> page = service.listar(pageable);
        return PagedModel.of(
                page.map(f -> EntityModel.of(f,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).buscar(f.getId())).withSelfRel()
                )).getContent(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    public EntityModel<Funcionario> buscar(@PathVariable Integer id) {
        Funcionario f = service.buscarPorId(id);
        return EntityModel.of(f,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).buscar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).listar(Pageable.unpaged())).withRel("todos"));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo funcionário")
    public ResponseEntity<Funcionario> criar(@RequestBody @Valid FuncionarioDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário por ID")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
