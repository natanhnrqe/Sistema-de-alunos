package com.system_alunos.Sistema_alunos.controller;

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
        return service.listar().stream().map(a -> new AlunoResponse(a.getNome(), a.getNota(),
                a.getSituacao())).toList();
    }

    @GetMapping("/{nome}")
    public AlunoResponse buscar(@PathVariable String nome) {
        Aluno aluno = service.buscar(nome);

        return new AlunoResponse(
                aluno.getNome(),
                aluno.getNota(),
                aluno.getSituacao()
        );
        }

    @PutMapping("/{nome}")
    public ResponseEntity<?> atualizar(@PathVariable String nome,@Valid @RequestBody AlunoRequest req){
        service.atualizarNota(nome, req.nota);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<?> remover(@PathVariable String nome) {
        service.remover(nome);

        return ResponseEntity.noContent().build();
    }
}
