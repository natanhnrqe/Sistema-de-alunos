package com.system_alunos.Sistema_alunos.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class AlunoNotaRequest {

    @Min(value = 0, message = "Nota minima é 0")
    @Max(value = 10, message = "Nota maxima é 10")
    public double nota;
}
