alter table aluno
alter column nome set not null;

alter table aluno
add constraint chk_aluno_nota_range
check ( nota >= 0 and nota <= 10 );