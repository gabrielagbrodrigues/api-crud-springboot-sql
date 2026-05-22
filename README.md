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
- Tratamento de exceções
- Validações básicas

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

Testes implementados:

- salvar usuário
- buscar usuário po ID
- listar usuários
- deletar usuário
- validação de exceções

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
