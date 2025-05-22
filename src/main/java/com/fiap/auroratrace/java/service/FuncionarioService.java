package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.FuncionarioDTO;
import com.fiap.auroratrace.java.model.Camera;
import com.fiap.auroratrace.java.model.Funcionario;
import com.fiap.auroratrace.java.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Page<Funcionario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Funcionario buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
    }

    public Funcionario criar(FuncionarioDTO dto) {
        Funcionario funcionario = Funcionario.builder()
                .nomeFunc(dto.getNomeFunc())
                .emailFunc(dto.getEmailFunc())
                .cargo(dto.getCargo())
                .cpfFunc(dto.getCpfFunc())
                .build();
        return repository.save(funcionario);
    }


    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }
        repository.deleteById(id);
    }
}
