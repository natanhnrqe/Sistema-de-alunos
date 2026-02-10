package com.system_alunos.Sistema_alunos.controller;

import com.system_alunos.Sistema_alunos.dtos.AlunoNotaRequest;
import com.system_alunos.Sistema_alunos.dtos.AlunoRequest;
import com.system_alunos.Sistema_alunos.dtos.AlunoResponse;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.service.AlunoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody AlunoRequest req){
        service.cadastrar(req.nome, req.nota);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<AlunoResponse> listar() {
        return service.listar().stream().map(a -> new AlunoResponse(a.getId(),a.getNome(), a.getNota(),
                a.getSituacao())).toList();
    }

    @GetMapping("/{id}")
    public AlunoResponse buscar(@PathVariable Long id) {
        Aluno aluno = service.buscar(id);

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getNota(),
                aluno.getSituacao()
        );
        }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@Valid @RequestBody AlunoNotaRequest req){
        service.atualizarNota(id, req.nota);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}
