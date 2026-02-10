package com.system_alunos.Sistema_alunos.dtos;

public class AlunoResponse {
    public Long id;
    public String nome;
    public double nota;
    public String situacao;

    public AlunoResponse(Long id, String nome, double nota, String situacao) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.situacao = situacao;
    }
}
