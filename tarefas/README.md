# Gerenciador de Tarefas

Um sistema simples de gerenciamento de tarefas e projetos, desenvolvido em Java com Javalin e Freemarker.

## Funcionalidades

- Cadastro e login de usuários
- Criação, edição e exclusão de projetos
- Adição, edição, exclusão e marcação de tarefas como concluídas
- Interface web responsiva com design moderno

## Tecnologias Utilizadas

- **Java 17**
- **Maven** para gerenciamento de dependências
- **Javalin** para o framework web
- **Freemarker** para templates
- **MySQL** para banco de dados
- **SLF4J** para logging

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL Server

## Configuração do Banco de Dados

1. Instale e configure o MySQL.
2. Crie um banco de dados (ex: `tarefas`).
3. Atualize as configurações em `DatabaseConfig.java` se necessário.

## Como Executar

1. Clone o repositório:

   ```bash
   git clone <url-do-repositorio>
   cd tarefas
   ```

2. Compile o projeto:

   ```bash
   mvn clean compile
   ```

3. Execute a aplicação:

   ```bash
   mvn exec:java -Dexec.mainClass="com.sistema.App"
   ```

   Ou, para gerar um JAR executável:

   ```bash
   mvn package
   java -jar target/gerenciador-tarefas-1.0-SNAPSHOT.jar
   ```

4. Acesse no navegador: `http://localhost:7000`

## Estrutura do Projeto

- `src/main/java/com/sistema/` - Código fonte
  - `controller/` - Controladores da aplicação
  - `dao/` - Acesso a dados
  - `model/` - Modelos de dados
  - `repository/` - Repositórios
- `src/main/resources/` - Templates Freemarker e CSS
- `pom.xml` - Configuração Maven

## Contribuição

Sinta-se à vontade para contribuir com melhorias!

## Licença

Este projeto é de código aberto.
