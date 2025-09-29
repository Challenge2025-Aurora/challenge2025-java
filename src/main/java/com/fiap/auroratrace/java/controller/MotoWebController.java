package com.fiap.auroratrace.java.controller;

import com.fiap.auroratrace.java.Enum.StatusMoto;
import com.fiap.auroratrace.java.dto.MotoDTO;
import com.fiap.auroratrace.java.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
public class MotoWebController {

    private final MotoService motoService;

    @Autowired
    public MotoWebController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public String listarMotos(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            Model model) {

        Page<MotoDTO> motosPage = motoService.listarTodas(pageable);

        model.addAttribute("motos", motosPage.getContent());
        model.addAttribute("page", motosPage);

        return "motos/lista";
    }

    @GetMapping("/nova")
    public String formularioCadastro(Model model) {
        model.addAttribute("motoDTO", new MotoDTO());
        return "motos/form";
    }

    @PostMapping("/nova")
    public String criarMoto(MotoDTO motoDTO, RedirectAttributes redirect) {
        motoService.criar(motoDTO);
        redirect.addFlashAttribute("mensagem", "Moto cadastrada com sucesso!");
        return "redirect:/motos";
    }

    @GetMapping("/{id}/editar")
    public String formularioEdicao(@PathVariable Long id, Model model) {
        MotoDTO moto = motoService.buscarPorId(id);
        model.addAttribute("motoDTO", moto);
        return "motos/form";
    }

    @PostMapping("/{id}/editar")
    public String atualizarMoto(@PathVariable Long id, MotoDTO motoDTO, RedirectAttributes redirect) {
        motoService.atualizar(id, motoDTO);
        redirect.addFlashAttribute("mensagem", "Moto atualizada com sucesso!");
        return "redirect:/motos";
    }

    @PostMapping("/{id}/deletar")
    public String deletarMoto(@PathVariable Long id, RedirectAttributes redirect) {
        motoService.deletar(id);
        redirect.addFlashAttribute("mensagem", "Moto excluída com sucesso!");
        return "redirect:/motos";
    }

    @GetMapping("/{id}/localizar")
    public String formularioLocalizacao(@PathVariable Long id, Model model) {
        MotoDTO moto = motoService.buscarPorId(id);
        model.addAttribute("motoDTO", moto);
        model.addAttribute("statusDisponiveis", StatusMoto.values());
        return "motos/localizar";
    }

    @PostMapping("/{id}/localizar")
    public String atualizarLocalizacao(@PathVariable Long id, MotoDTO motoDTO, RedirectAttributes redirect) {
        motoService.atualizar(id, motoDTO);
        redirect.addFlashAttribute("mensagem", "Localização da moto " + motoDTO.getPlaca() + " atualizada para " + motoDTO.getUltimoSetor() + "/" + motoDTO.getUltimoSlot() + ".");
        return "redirect:/motos";
    }
}