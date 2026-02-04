package com.system_alunos.Sistema_alunos.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AlunoRequest {

    @NotBlank(message = "Nome nao pode estar vazio")
    public String nome;

    @Min(value = 0, message = "Nota minima é 0")
    @Max(value = 10, message = "Nota maxima é 10")
    public double nota;
}
