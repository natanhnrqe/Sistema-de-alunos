# 📚 Sistema de Alunos – Java & Spring

## 📌 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de consolidar e aplicar, na prática, os conhecimentos que venho adquirindo em **Java** e **Spring Framework**.

Em vez de criar múltiplos projetos isolados para cada exercício ou desafio realizado durante os estudos, optei por centralizar todos os desafios, testes e aprendizados em um único projeto. Dessa forma, consigo evoluir a aplicação continuamente, aplicando novos conceitos em uma base já existente e simulando um ambiente mais próximo de um sistema real.

O projeto funciona como um laboratório prático de evolução técnica.

---

## 🎯 Objetivos

- Aplicar conceitos fundamentais de Java  
- Praticar desenvolvimento com Spring Boot  
- Implementar APIs REST  
- Trabalhar com arquitetura em camadas  
- Aplicar boas práticas de organização e separação de responsabilidades  
- Utilizar tratamento de exceções  
- Trabalhar com persistência de dados usando Spring Data JPA  
- Evoluir a aplicação conforme avanço nos estudos  

---

## 🛠️ Tecnologias Utilizadas

- Java  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Maven  
- Banco de dados (H2, MySQL ou outro)

---

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura em camadas organizada por responsabilidades dentro do pacote principal:

```bash
com.system_alunos
├── controller
├── dtos
├── error
├── exceptions
├── model
├── repository
├── service
└── SistemaAlunosApplication
```

## 📌 Descrição dos Pacotes

### 📦 controller

- Responsável por expor os endpoints da API REST.
- Recebe as requisições HTTP.
- Delega o processamento para a camada `service`.
- Retorna as respostas apropriadas com seus respectivos status HTTP.

---

### 📦 service

- Camada onde ficam as regras de negócio da aplicação.
- Realiza validações.
- Processa dados antes da persistência.
- Coordena a comunicação entre `controller` e `repository`.

---

### 📦 repository

- Responsável pelo acesso aos dados.
- Utiliza **Spring Data JPA**.
- Realiza operações de persistência como:
  - `save`
  - `findById`
  - `findAll`
  - `delete`

---

### 📦 model

- Contém as entidades do sistema.
- Classes anotadas com `@Entity`.
- Representam as tabelas do banco de dados.

---

### 📦 dtos

- Contém os **Data Transfer Objects (DTOs)**.
- Controla os dados de entrada e saída da aplicação.
- Evita expor diretamente as entidades do banco.

---

### 📦 exceptions

- Contém exceções personalizadas da aplicação.
- Representa erros de regra de negócio.

---

### 📦 error

- Responsável pela padronização das respostas de erro.
- Define o payload retornado quando ocorre uma exceção.
- Garante consistência nas respostas da API.

---

### 🚀 SistemaAlunosApplication

- Classe principal da aplicação.
- Contém a anotação `@SpringBootApplication`.
- Responsável por inicializar o contexto do Spring Boot.

  ---

## 🚀 Evolução Contínua

Este projeto não é um sistema finalizado.

Ele será constantemente atualizado conforme novos conteúdos e desafios forem estudados em **Java** e **Spring**.

A proposta é manter um único projeto robusto e evolutivo, em vez de diversos projetos pequenos e desconectados.

---

## 📖 Motivação

A decisão de centralizar todos os aprendizados em um único projeto foi estratégica:

- Permite visualizar claramente minha evolução técnica.
- Facilita aplicar novos conceitos em uma base já existente.
- Simula um cenário mais próximo de projetos reais.
- Mantém o código organizado e progressivamente mais estruturado.


