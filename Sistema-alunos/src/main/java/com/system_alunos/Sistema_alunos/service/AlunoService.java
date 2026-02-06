package com.system_alunos.Sistema_alunos.service;

import com.system_alunos.Sistema_alunos.exceptions.AlunoAlreadyExistException;
import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlunoService {

    private Map<String, Aluno> alunos = new HashMap<>();

   public void cadastrar(String nome, double nota){
       String chave = nome.toLowerCase();
       if (alunos.containsKey(chave)) {
           throw new AlunoAlreadyExistException("Aluno ja existe");
       }

       alunos.put(chave, new Aluno(nome, nota));
   }

    public List<Aluno> listar(){
       return new ArrayList<>(alunos.values());
    }

    public Aluno buscar(String nome){
        Aluno aluno = alunos.get(nome.toLowerCase());

        if (aluno == null){
            throw new AlunoNotFoundException("Aluno nao encontrado");
        }
        return aluno;
    }

    public void atualizarNota(String nome, double nota){
       Aluno a = buscar(nome);
       a.setNota(nota);
    }

    public void remover(String nome){
        Aluno aluno = alunos.remove(nome.toLowerCase());
        if (aluno == null){
            throw new AlunoNotFoundException("Aluno nao encontrado");
        }
    }
}
