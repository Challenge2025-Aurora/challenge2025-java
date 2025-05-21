package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.ImagemDTO;
import com.fiap.auroratrace.java.model.Imagem;
import com.fiap.auroratrace.java.service.ImagemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ImagemService service;

    public ImagemController(ImagemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Imagem> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagem> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Imagem> criar(@RequestBody @Valid ImagemDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
