package com.system_alunos.Sistema_alunos;

import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.repository.AlunoRepository;
import com.system_alunos.Sistema_alunos.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


	@ExtendWith(MockitoExtension.class)
	class AlunoServiceTests{

		@Mock
		private AlunoRepository repository;

		@InjectMocks
		private AlunoService service;

		@Test
		void deveRetornarAlunoQuandoExistir(){

			Aluno aluno = new Aluno();
			aluno.setId(1L);
			aluno.setNome("Roberto");

			when(repository.findById(1L)).thenReturn(Optional.of(aluno));

			Aluno resultado = service.buscar(1L);

			assertEquals("Roberto", resultado.getNome());
		}

		@Test
		void deveLancarExcecaoQuandoNaoExistir(){

			when(repository.findById(1L)).thenReturn(Optional.empty());

			assertThrows(AlunoNotFoundException.class,
					() -> service.buscar(1L));
		}

	}

