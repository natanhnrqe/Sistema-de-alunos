package com.system_alunos.Sistema_alunos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CursoRequest {


    @NotBlank(message = "Nome nao pode estar vazio")
    public String nome;
}
