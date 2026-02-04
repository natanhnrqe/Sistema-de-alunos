package com.system_alunos.Sistema_alunos.service;

import com.system_alunos.Sistema_alunos.exceptions.AlunoAlreadyExistException;
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

    public Optional<Aluno> buscar(String nome){
        return Optional.ofNullable(alunos.get(nome.toLowerCase()));
    }

    public boolean atualizarNota(String nome, double nota){
       Aluno a = alunos.get(nome.toLowerCase());

       if (a == null) return false;

       a.setNota(nota);
       return true;
    }

    public boolean remover(String nome){
        return alunos.remove(nome.toLowerCase()) != null;
    }
}
