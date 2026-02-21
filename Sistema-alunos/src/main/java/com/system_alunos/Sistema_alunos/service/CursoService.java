package com.system_alunos.Sistema_alunos.service;

import com.system_alunos.Sistema_alunos.dtos.CursoResponse;
import com.system_alunos.Sistema_alunos.exceptions.RecursoNaoEncontradoException;
import com.system_alunos.Sistema_alunos.model.Curso;
import com.system_alunos.Sistema_alunos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso cadastrar(String nome){

        Curso curso = new Curso();
        curso.setNome(nome);

        return cursoRepository.save(curso);
    }

    public List<CursoResponse> listarCursos(){

        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(curso -> new CursoResponse(
                curso.getId(),
                curso.getNome()
        )).toList();
    }

    public Curso buscarCurso(Long id){
        return cursoRepository.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Curso nao encontrado"));
    }

    public void removerCurso(Long id){
        Curso curso = buscarCurso(id);
        cursoRepository.delete(curso);

    }
}
