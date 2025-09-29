package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.DeteccaoDTO;
import com.fiap.auroratrace.java.service.DeteccaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/deteccoes")
public class DeteccaoWebController {

    private final DeteccaoService deteccaoService;

    @Autowired
    public DeteccaoWebController(DeteccaoService deteccaoService) {
        this.deteccaoService = deteccaoService;
    }

    @GetMapping
    public String listarDeteccoes(
            @PageableDefault(size = 15, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        Page<DeteccaoDTO> deteccoesPage = deteccaoService.listarTodas(pageable);

        model.addAttribute("deteccoes", deteccoesPage.getContent());
        model.addAttribute("page", deteccoesPage);

        return "deteccoes/lista";
    }

    @GetMapping("/{id}")
    public String verDetalhes(@PathVariable Long id, Model model) {
        DeteccaoDTO deteccao = deteccaoService.buscarPorId(id);

        model.addAttribute("deteccao", deteccao);

        return "deteccoes/detalhe";
    }
}