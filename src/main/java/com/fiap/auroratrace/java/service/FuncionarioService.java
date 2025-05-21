package com.fiap.auroratrace.java.service;

import com.fiap.auroratrace.java.dto.FuncionarioDTO;
import com.fiap.auroratrace.java.model.Funcionario;
import com.fiap.auroratrace.java.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
    }

    public Funcionario criar(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario(dto.getNome(), dto.getMatricula(), dto.getCargo(), dto.getTelefone());
        return repository.save(funcionario);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }
        repository.deleteById(id);
    }
}
