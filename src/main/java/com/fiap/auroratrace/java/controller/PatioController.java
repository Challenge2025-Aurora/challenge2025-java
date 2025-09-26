package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.service.PatioService;
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
@RequestMapping("/api/patios")
@Tag(name = "Pátios", description = "Gerenciamento de pátios e suas dimensões")
public class PatioController {

    private final PatioService patioService;
    private final PagedResourcesAssembler<PatioDTO> pagedResourcesAssembler;

    @Autowired
    public PatioController(PatioService patioService, PagedResourcesAssembler<PatioDTO> pagedResourcesAssembler) {
        this.patioService = patioService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Operation(summary = "Lista todos os pátios com paginação")
    @GetMapping
    public PagedModel<EntityModel<PatioDTO>> listarTodos(Pageable pageable) {
        Page<PatioDTO> patios = patioService.listarTodos(pageable);
        return pagedResourcesAssembler.toModel(patios, patio -> {
            EntityModel<PatioDTO> resource = EntityModel.of(patio);
            resource.add(linkTo(methodOn(PatioController.class).buscarPorId(patio.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca um pátio pelo ID")
    @GetMapping("/{id}")
    public EntityModel<PatioDTO> buscarPorId(@PathVariable Integer id) {
        PatioDTO patio = patioService.buscarPorId(id);
        EntityModel<PatioDTO> resource = EntityModel.of(patio);
        resource.add(linkTo(methodOn(PatioController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(PatioController.class).listarTodos(Pageable.unpaged())).withRel("todos"));
        return resource;
    }

    @Operation(summary = "Cria um novo pátio")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<PatioDTO> criar(@RequestBody @Valid PatioDTO patioDTO) {
        PatioDTO novoPatio = patioService.criar(patioDTO);
        return EntityModel.of(novoPatio,
                linkTo(methodOn(PatioController.class).buscarPorId(novoPatio.getId())).withSelfRel(),
                linkTo(methodOn(PatioController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }

    @Operation(summary = "Atualiza um pátio existente")
    @PutMapping("/{id}")
    public EntityModel<PatioDTO> atualizar(@PathVariable Integer id, @RequestBody @Valid PatioDTO patioDTO) {
        PatioDTO patioAtualizado = patioService.atualizar(id, patioDTO);
        return EntityModel.of(patioAtualizado,
                linkTo(methodOn(PatioController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(PatioController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }

    @Operation(summary = "Deleta um pátio pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        patioService.deletar(id);
    }

    @Operation(summary = "Busca um pátio pelo nome")
    @GetMapping("/nome/{nome}")
    public EntityModel<PatioDTO> buscarPorNome(@PathVariable String nome) {
        PatioDTO patio = patioService.buscarPorNome(nome);
        return EntityModel.of(patio,
                linkTo(methodOn(PatioController.class).buscarPorNome(nome)).withSelfRel(),
                linkTo(methodOn(PatioController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }
}