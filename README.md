# API CRUD com Spring Boot e SQL

API REST desenvolvida para gerenciamento de usuários, implementando operações CRUD (Create, Read, Update, Delete) com integração a banco de dados relacional.

##  Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Banco de dados SQL
- Maven
- Swagger/OpenAPI
- JUnit 5
- Mockito

##  Funcionalidades

- Criar usuário
- Listar todos os usuários
- Buscar usuário por ID
- Atualizar dados do usuário
- Deletar usuário
- Validação de CPF único
- Validação de campos obrigatórios
- Tratamento global de exceções
- Respostas HTTP padronizadas

##  Endpoints

| Método | Endpoint        | Descrição                  |
|--------|---------------|----------------------------|
| POST   | /usuarios     | Criar novo usuário         |
| GET    | /usuarios     | Listar todos os usuários   |
| GET    | /usuarios/{id}| Buscar usuário por ID      |
| PUT    | /usuarios/{id}| Atualizar usuário          |
| DELETE | /usuarios/{id}| Deletar usuário            |


## Documentação Swagger
Após iniciar a aplicação:

http://localhost:8080/swagger-ui.html

---
## Testes Unitários

O projeto possui testes unitários utilizando:

- JUnit 5
- Mockito

## Testes implementados:

- Salvar usuário com sucesso
- Buscar usuário por ID
- Listar usuários
- Deletar usuário
- Usuário não encontrado
- CPF já cadastrado
- Tratamento de exceções

## Regras de Negócio:

- Não é permitido cadastrar dois usuários com o mesmo CPF.
- O CPF é validado antes da persistência.
- Usuários inexistentes retornam HTTP 404
- Tentativas de cadastro duplicado retornam HTTP 409.

---

## Banco de Dados

Banco de utilizado:

- MySQL

Configuração realizada no arquivo:

src/main/resources/application.properties

---

## ⚙️ Como executar o projeto

```bash
# Clonar o repositório
git clone https://github.com/gabrielagbrodrigues/api-crud-springboot-sql.git

# Entrar na pasta
cd api-crud-springboot-sql

# Rodar o projeto
./mvnw spring-boot:run
