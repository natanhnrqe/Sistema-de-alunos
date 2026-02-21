package com.system_alunos.Sistema_alunos.controller;


import com.system_alunos.Sistema_alunos.dtos.CursoRequest;
import com.system_alunos.Sistema_alunos.dtos.CursoResponse;
import com.system_alunos.Sistema_alunos.model.Curso;
import com.system_alunos.Sistema_alunos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CursoRequest req){

        service.cadastrar(req.nome);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> listarCursos(){

        List<CursoResponse> cursos = service.listarCursos();

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public CursoResponse buscarCurso(@PathVariable Long id){
        Curso curso = service.buscarCurso(id);

        return new CursoResponse(curso.getId(), curso.getNome());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCurso(@PathVariable Long id){
        service.removerCurso(id);

        return ResponseEntity.noContent().build();

    }
}
