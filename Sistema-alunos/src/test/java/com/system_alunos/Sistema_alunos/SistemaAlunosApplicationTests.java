package com.system_alunos.Sistema_alunos;

import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.exceptions.RecursoNaoEncontradoException;
import com.system_alunos.Sistema_alunos.model.Aluno;
import com.system_alunos.Sistema_alunos.model.Curso;
import com.system_alunos.Sistema_alunos.repository.AlunoRepository;
import com.system_alunos.Sistema_alunos.repository.CursoRepository;
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
		private AlunoRepository alunoRepository;

		@Mock
		private CursoRepository cursoRepository;

		@InjectMocks
		private AlunoService service;

		@Test
		void deveRetornarAlunoQuandoExistir(){

			Aluno aluno = new Aluno();
			aluno.setId(1L);
			aluno.setNome("Roberto");

			when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

			Aluno resultado = service.buscar(1L);

			assertEquals("Roberto", resultado.getNome());
		}

		@Test
		void deveLancarExcecaoQuandoAlunoNaoExistir(){

			when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

			assertThrows(AlunoNotFoundException.class,
					() -> service.buscar(1L));
		}

		@Test
		void deveLancarExcecaoQuandoCursoNaoExistir(){
			when(cursoRepository.findById(999L)).thenReturn(Optional.empty());

			assertThrows(RecursoNaoEncontradoException.class, () -> {
				service.cadastrar("Claudete", 9, 999L);
			});

		}
		@Test
		void deveRetornarCursoQuandoExistir(){

			Curso curso = new Curso();
			curso.setId(1L);
			curso.setNome("Java");

			when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

			when(alunoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

			assertDoesNotThrow(() -> {
				service.cadastrar("Joao", 2, 1L);
			});
		}

	}

