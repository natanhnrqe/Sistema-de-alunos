package com.system_alunos.Sistema_alunos.service;

import com.system_alunos.Sistema_alunos.exceptions.AlunoAlreadyExistException;
import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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

    public Aluno buscar(Long id){

        return repository.findById(id)
        .orElseThrow(() -> new AlunoNotFoundException("Aluno nao encontrado"));
    }

    public void atualizarNota(Long id, double nota){
       Aluno aluno = buscar(id);
       aluno.setNota(nota);
       repository.save(aluno);
    }

    public void remover(Long id){
        Aluno aluno = buscar(id);
       repository.delete(aluno);
    }
    public Page<Aluno> listarPaginado(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<Aluno> listarComFiltro(String nome, Double notaMin, Pageable pageable){
        if (nome != null && notaMin != null){
            return repository.findByNomeContainingIgnoreCaseAndNotaGreaterThanEqual(nome, notaMin, pageable);
        }
        if (nome != null){
            return repository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        if (notaMin != null){
            return repository.findByNotaGreaterThanEqual(notaMin, pageable);
        }
        return repository.findAll(pageable);
    }
}
