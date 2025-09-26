package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.DeteccaoDTO;
import com.fiap.auroratrace.java.service.DeteccaoService;
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
@RequestMapping("/api/deteccoes")
@Tag(name = "Detecções", description = "Registros de detecção de veículos via visão computacional")
public class DeteccaoController {

    private final DeteccaoService deteccaoService;
    private final PagedResourcesAssembler<DeteccaoDTO> pagedResourcesAssembler;

    @Autowired
    public DeteccaoController(DeteccaoService deteccaoService, PagedResourcesAssembler<DeteccaoDTO> pagedResourcesAssembler) {
        this.deteccaoService = deteccaoService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Operation(summary = "Lista todas as detecções com paginação")
    @GetMapping
    public PagedModel<EntityModel<DeteccaoDTO>> listarTodas(Pageable pageable) {
        Page<DeteccaoDTO> deteccoes = deteccaoService.listarTodas(pageable);
        return pagedResourcesAssembler.toModel(deteccoes, deteccao -> {
            EntityModel<DeteccaoDTO> resource = EntityModel.of(deteccao);
            resource.add(linkTo(methodOn(DeteccaoController.class).buscarPorId(deteccao.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca uma detecção pelo ID")
    @GetMapping("/{id}")
    public EntityModel<DeteccaoDTO> buscarPorId(@PathVariable Long id) {
        DeteccaoDTO deteccao = deteccaoService.buscarPorId(id);
        EntityModel<DeteccaoDTO> resource = EntityModel.of(deteccao);
        resource.add(linkTo(methodOn(DeteccaoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(DeteccaoController.class).listarTodas(Pageable.unpaged())).withRel("todas"));
        return resource;
    }

    @Operation(summary = "Cria uma nova detecção")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<DeteccaoDTO> criar(@RequestBody @Valid DeteccaoDTO deteccaoDTO) {
        DeteccaoDTO novaDeteccao = deteccaoService.criar(deteccaoDTO);
        return EntityModel.of(novaDeteccao,
                linkTo(methodOn(DeteccaoController.class).buscarPorId(novaDeteccao.getId())).withSelfRel(),
                linkTo(methodOn(DeteccaoController.class).listarTodas(Pageable.unpaged())).withRel("todas")
        );
    }

    @Operation(summary = "Deleta uma detecção pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        deteccaoService.deletar(id);
    }

    @Operation(summary = "Busca detecções por placa")
    @GetMapping("/placa/{placa}")
    public PagedModel<EntityModel<DeteccaoDTO>> buscarPorPlaca(@PathVariable String placa, Pageable pageable) {
        Page<DeteccaoDTO> deteccoes = deteccaoService.buscarPorPlaca(placa, pageable);
        return pagedResourcesAssembler.toModel(deteccoes, deteccao -> {
            EntityModel<DeteccaoDTO> resource = EntityModel.of(deteccao);
            resource.add(linkTo(methodOn(DeteccaoController.class).buscarPorId(deteccao.getId())).withSelfRel());
            return resource;
        });
    }

    @Operation(summary = "Busca a detecção mais recente para uma placa")
    @GetMapping("/placa/{placa}/ultima")
    public EntityModel<DeteccaoDTO> buscarUltimaPorPlaca(@PathVariable String placa) {
        DeteccaoDTO deteccao = deteccaoService.buscarUltimaPorPlaca(placa);
        return EntityModel.of(deteccao,
                linkTo(methodOn(DeteccaoController.class).buscarUltimaPorPlaca(placa)).withSelfRel()
        );
    }
}