package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.model.Moto;
import com.fiap.auroratrace.java.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Moto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Moto> criar(@RequestBody @Valid MotoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}