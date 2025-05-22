package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.PatioDTO;
import com.fiap.auroratrace.java.model.Patio;
import com.fiap.auroratrace.java.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patios")
public class PatioController {

    private final PatioService service;

    public PatioController(PatioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patio> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Patio> criar(@RequestBody @Valid PatioDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}