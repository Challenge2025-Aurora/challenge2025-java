package com.fiap.auroratrace.java.repository;

import com.fiap.auroratrace.java.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByCpfFunc(String cpfFunc);
    Optional<Funcionario> findByEmailFunc(String emailFunc);
}
