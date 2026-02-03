package com.system_alunos.Sistema_alunos.dtos;

public class AlunoResponse {
    public String nome;
    public double nota;
    public String situacao;

    public AlunoResponse(String nome, double nota, String situacao) {
        this.nome = nome;
        this.nota = nota;
        this.situacao = situacao;
    }
}
