package com.system_alunos.Sistema_alunos.service;

import com.system_alunos.Sistema_alunos.exceptions.AlunoAlreadyExistException;
import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String nome, double nota){
       if (repository.existsByNomeIgnoreCase(nome)) {
           throw new AlunoAlreadyExistException("Aluno ja existe");
       }
        repository.save(new Aluno(nome, nota));

   }

    public List<Aluno> listar(){
       return repository.findAll();
    }

    public Aluno buscar(String nome){

        return repository.findByNomeIgnoreCase(nome)
        .orElseThrow(() -> new AlunoNotFoundException("Aluno nao encontrado"));
    }

    public void atualizarNota(String nome, double nota){
       Aluno aluno = buscar(nome);
       aluno.setNota(nota);
       repository.save(aluno);
    }

    public void remover(String nome){
        Aluno aluno = buscar(nome);
       repository.delete(aluno);
    }
}
