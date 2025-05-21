package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.CameraDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.service.CameraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    private final CameraService service;

    public CameraController(CameraService service) {
        this.service = service;
    }

    @GetMapping
    public List<Camera> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Camera> criar(@RequestBody @Valid CameraDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
