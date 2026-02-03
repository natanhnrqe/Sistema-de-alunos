package com.system_alunos.Sistema_alunos.controller;

import com.system_alunos.Sistema_alunos.dtos.AlunoRequest;
import com.system_alunos.Sistema_alunos.dtos.AlunoResponse;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.service.AlunoService;
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
    public ResponseEntity<?> cadastrar(@RequestBody AlunoRequest req){
        boolean ok = service.cadastrar(req.nome, req.nota);

        if (!ok) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluno ja existe");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<AlunoResponse> listar() {
        return service.listar().stream().map(a -> new AlunoResponse(a.getNome(), a.getNota(),
                a.getSituacao())).toList();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<AlunoResponse> buscar(@PathVariable String nome) {
        return service.buscar(nome).map(a -> ResponseEntity.ok(
                new AlunoResponse(
                        a.getNome(),
                        a.getNota(),
                        a.getSituacao()
                )
        )).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{nome}")
    public ResponseEntity<?> atualizar(@PathVariable String nome, @RequestBody AlunoRequest req){
        boolean ok = service.atualizarNota(nome, req.nota);

        if (!ok) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<?> remover(@PathVariable String nome) {
        boolean ok = service.remover(nome);

        if (!ok) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
