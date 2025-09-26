package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.service.MotoService;
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
@RequestMapping("/api/motos")
@Tag(name = "Motos", description = "Gerenciamento de Motos e Status de Localização")
public class MotoController {

    private final MotoService motoService;
    private final PagedResourcesAssembler<MotoDTO> pagedResourcesAssembler;

    @Autowired
    public MotoController(MotoService motoService, PagedResourcesAssembler<MotoDTO> pagedResourcesAssembler) {
        this.motoService = motoService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Operation(summary = "Lista todas as motos com paginação")
    @GetMapping
    public PagedModel<EntityModel<MotoDTO>> listarTodas(Pageable pageable) {
        Page<MotoDTO> motos = motoService.listarTodas(pageable);
        return pagedResourcesAssembler.toModel(motos, moto -> {
            EntityModel<MotoDTO> resource = EntityModel.of(moto);
            resource.add(linkTo(methodOn(MotoController.class).buscarPorId(moto.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca uma moto pelo ID")
    @GetMapping("/{id}")
    public EntityModel<MotoDTO> buscarPorId(@PathVariable Long id) {
        MotoDTO moto = motoService.buscarPorId(id);
        EntityModel<MotoDTO> resource = EntityModel.of(moto);
        resource.add(linkTo(methodOn(MotoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(MotoController.class).listarTodas(Pageable.unpaged())).withRel("todas"));
        return resource;
    }

    @Operation(summary = "Cria uma nova moto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<MotoDTO> criar(@RequestBody @Valid MotoDTO motoDTO) {
        MotoDTO novaMoto = motoService.criar(motoDTO);
        return EntityModel.of(novaMoto,
                linkTo(methodOn(MotoController.class).buscarPorId(novaMoto.getId())).withSelfRel(),
                linkTo(methodOn(MotoController.class).listarTodas(Pageable.unpaged())).withRel("todas")
        );
    }

    @Operation(summary = "Atualiza uma moto existente")
    @PutMapping("/{id}")
    public EntityModel<MotoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MotoDTO motoDTO) {
        MotoDTO motoAtualizada = motoService.atualizar(id, motoDTO);
        return EntityModel.of(motoAtualizada,
                linkTo(methodOn(MotoController.class).buscarPorId(id)).withSelfRel(),
                linkTo(methodOn(MotoController.class).listarTodas(Pageable.unpaged())).withRel("todas")
        );
    }

    @Operation(summary = "Deleta uma moto pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        motoService.deletar(id);
    }

    @Operation(summary = "Busca motos pelo status")
    @GetMapping("/status/{status}")
    public PagedModel<EntityModel<MotoDTO>> buscarPorStatus(@PathVariable String status, Pageable pageable) {
        Page<MotoDTO> motos = motoService.buscarPorStatus(status, pageable);
        return pagedResourcesAssembler.toModel(motos, moto -> {
            EntityModel<MotoDTO> resource = EntityModel.of(moto);
            resource.add(linkTo(methodOn(MotoController.class).buscarPorId(moto.getId())).withSelfRel());
            return resource;
        });
    }
}