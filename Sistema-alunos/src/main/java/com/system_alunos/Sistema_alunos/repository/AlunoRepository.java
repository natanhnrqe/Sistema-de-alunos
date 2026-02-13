package com.system_alunos.Sistema_alunos.repository;

import com.system_alunos.Sistema_alunos.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);

    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Aluno> findByNotaGreaterThanEqual(Double nota, Pageable pageable);

    Page<Aluno> findByNomeContainingIgnoreCaseAndNotaGreaterThanEqual(String nome, Double nota,  Pageable pageable);






}
