# API CRUD com Spring Boot e SQL

API REST desenvolvida para gerenciamento de usuários, implementando operações CRUD (Create, Read, Update, Delete) com integração a banco de dados relacional.

##  Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Banco de dados SQL
- Maven

##  Funcionalidades

- Criar usuário
- Listar todos os usuários
- Buscar usuário por ID
- Atualizar dados do usuário
- Deletar usuário

##  Endpoints

| Método | Endpoint        | Descrição                  |
|--------|---------------|----------------------------|
| POST   | /usuarios     | Criar novo usuário         |
| GET    | /usuarios     | Listar todos os usuários   |
| GET    | /usuarios/{id}| Buscar usuário por ID      |
| PUT    | /usuarios/{id}| Atualizar usuário          |
| DELETE | /usuarios/{id}| Deletar usuário            |

## ⚙️ Como executar o projeto

```bash
# Clonar o repositório
git clone https://github.com/gabrielagbrodrigues/api-crud-springboot-sql.git

# Entrar na pasta
cd api-crud-springboot-sql

# Rodar o projeto
./mvnw spring-boot:run
