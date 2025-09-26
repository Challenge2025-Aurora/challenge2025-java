package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.SetorDTO;
import com.fiap.auroratrace.java.service.SetorService;
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
@RequestMapping("/api/setores")
@Tag(name = "Setores", description = "Gerenciamento dos setores dentro dos pátios")
public class SetorController {

    private final SetorService setorService;
    private final PagedResourcesAssembler<SetorDTO> pagedResourcesAssembler;

    @Autowired
    public SetorController(SetorService setorService, PagedResourcesAssembler<SetorDTO> pagedResourcesAssembler) {
        this.setorService = setorService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Operation(summary = "Lista todos os setores com paginação")
    @GetMapping
    public PagedModel<EntityModel<SetorDTO>> listarTodos(Pageable pageable) {
        Page<SetorDTO> setores = setorService.listarTodos(pageable);
        return pagedResourcesAssembler.toModel(setores, setor -> {
            EntityModel<SetorDTO> resource = EntityModel.of(setor);
            resource.add(linkTo(methodOn(SetorController.class).buscarPorId(setor.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca um setor pelo ID")
    @GetMapping("/{id}")
    public EntityModel<SetorDTO> buscarPorId(@PathVariable Long id) {
        SetorDTO setor = setorService.buscarPorId(id);
        EntityModel<SetorDTO> resource = EntityModel.of(setor);
        resource.add(linkTo(methodOn(SetorController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(SetorController.class).listarTodos(Pageable.unpaged())).withRel("todos"));
        return resource;
    }

    @Operation(summary = "Cria um novo setor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<SetorDTO> criar(@RequestBody @Valid SetorDTO setorDTO) {
        SetorDTO novoSetor = setorService.criar(setorDTO);
        return EntityModel.of(novoSetor,
                linkTo(methodOn(SetorController.class).buscarPorId(novoSetor.getId())).withSelfRel(),
                linkTo(methodOn(SetorController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }

    @Operation(summary = "Atualiza um setor existente")
    @PutMapping("/{id}")
    public EntityModel<SetorDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SetorDTO setorDTO) {
        SetorDTO setorAtualizado = setorService.atualizar(id, setorDTO);
        return EntityModel.of(setorAtualizado,
                linkTo(methodOn(SetorController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(SetorController.class).listarTodos(Pageable.unpaged())).withRel("todos")
        );
    }

    @Operation(summary = "Deleta um setor pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        setorService.deletar(id);
    }

    @Operation(summary = "Busca setores por ID do pátio")
    @GetMapping("/patio/{patioId}")
    public PagedModel<EntityModel<SetorDTO>> buscarPorPatioId(@PathVariable Long patioId, Pageable pageable) {
        Page<SetorDTO> setores = setorService.buscarPorPatioId(patioId, pageable);
        return pagedResourcesAssembler.toModel(setores, setor -> {
            EntityModel<SetorDTO> resource = EntityModel.of(setor);
            resource.add(linkTo(methodOn(SetorController.class).buscarPorId(setor.getId())).withSelfRel());
            return resource;
        });
    }
}