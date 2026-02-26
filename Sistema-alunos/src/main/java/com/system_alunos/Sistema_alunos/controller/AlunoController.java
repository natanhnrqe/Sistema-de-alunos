package com.system_alunos.Sistema_alunos.controller;

import com.system_alunos.Sistema_alunos.dtos.AlunoNotaRequest;
import com.system_alunos.Sistema_alunos.dtos.AlunoRequest;
import com.system_alunos.Sistema_alunos.dtos.AlunoResponse;
import com.system_alunos.Sistema_alunos.dtos.PageResponse;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.service.AlunoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody AlunoRequest req){
        service.cadastrar(req.nome, req.nota, req.cursoId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public PageResponse<AlunoResponse> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double notaMin,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {

        if (pageable.getPageSize() > 20){
        throw new IllegalArgumentException("Tamanho maximo da pagina é 20");
        }

        Page<AlunoResponse> page = service.listarComFiltro(nome, notaMin, pageable).map(a -> new AlunoResponse(a.getId(),a.getNome(), a.getNota(),
                a.getSituacao()));

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );
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
