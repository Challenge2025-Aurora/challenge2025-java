package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.dto.EventoDTO;
import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.service.EventoService;
import com.fiap.auroratrace.java.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoWebController {

    private final EventoService eventoService;
    private final MotoService motoService;

    @Autowired
    public EventoWebController(EventoService eventoService, MotoService motoService) {
        this.eventoService = eventoService;
        this.motoService = motoService;
    }

    @GetMapping
    public String listarEventos(
            @PageableDefault(size = 10, sort = "criadoEm", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable,
            Model model) {

        Page<EventoDTO> eventosPage = eventoService.listarTodos(pageable);

        model.addAttribute("eventos", eventosPage.getContent());
        model.addAttribute("page", eventosPage);

        return "eventos/lista";
    }

    @GetMapping("/novo")
    public String formularioCadastro(Model model) {
        model.addAttribute("eventoDTO", new EventoDTO());

        List<MotoDTO> todasMotos = motoService.listarTodas(Pageable.unpaged()).getContent();
        model.addAttribute("motos", todasMotos);

        return "eventos/form";
    }

    @PostMapping("/novo")
    public String criarEvento(EventoDTO eventoDTO, RedirectAttributes redirect) {
        eventoService.criar(eventoDTO);
        redirect.addFlashAttribute("mensagem", "Evento registrado com sucesso!");
        return "redirect:/eventos";
    }

    @PostMapping("/{id}/deletar")
    public String deletarEvento(@PathVariable Long id, RedirectAttributes redirect) {
        eventoService.deletar(id);
        redirect.addFlashAttribute("mensagem", "Evento excluído com sucesso.");
        return "redirect:/eventos";
    }
}