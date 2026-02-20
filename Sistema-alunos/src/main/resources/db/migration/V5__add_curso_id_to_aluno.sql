alter table aluno
add column curso_id BIGINT;

alter table aluno
add constraint fk_aluno_curso
foreign key (curso_id)
references curso(id);