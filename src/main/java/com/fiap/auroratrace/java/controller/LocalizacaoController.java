package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.LocalizacaoDTO;
import com.fiap.auroratrace.java.model.Localizacao;
import com.fiap.auroratrace.java.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Localizacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Localizacao> criar(@RequestBody @Valid LocalizacaoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
