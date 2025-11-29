<h1 align="center">🌟 GastroReserva: Sistema de Reserva de Restaurantes 🌟</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=spring&logoColor=white" alt="Spring Boot 3.x">
  <img src="https://img.shields.io/badge/Banco_de_Dados-SQL_Server_|_H2-orange" alt="SQL Server | H2">
  <img src="https://img.shields.io/badge/Arquitetura-API_REST-purple" alt="API REST">
</p>

---

## 📋 Sumário

- [🔍 Visão Geral](#-visão-geral)
- [🛠️ Tecnologias e Padrões](#️-tecnologias-e-padrões)
- [🏛️ Arquitetura da Solução](#️-arquitetura-da-solução)
- [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
  - [Pré-requisitos](#pré-requisitos)
  - [Passo a Passo da Execução](#passo-a-passo-da-execução)
  - [Configurando para SQL Server](#configurando-para-sql-server)
- [📖 Acesso, Teste e Documentação da API](#-acesso-teste-e-documentação-da-api)
  - [Autenticação](#autenticação)
  - [Testando com Postman](#testando-com-postman)
  - [Documentação Interativa (Swagger)](#documentação-interativa-swagger)
  - [Acesso ao H2 Console](#acesso-ao-h2-console)
- [🤝 Equipe do Projeto](#-equipe-do-projeto)

---

## 🔍 Visão Geral

O **GastroReserva** é um projeto Full Stack desenvolvido como requisito para a conclusão do curso de Desenvolvimento da Universidade CEUB.

O sistema tem como objetivo principal otimizar e simplificar a experiência de reserva de mesas, beneficiando tanto os clientes quanto os restaurantes. Através de uma plataforma centralizada, os clientes podem descobrir, reservar e gerenciar suas idas a diversos estabelecimentos, enquanto os restaurantes ganham uma ferramenta poderosa para gerenciar suas mesas, otimizar a ocupação e aprimorar a comunicação com o público.

O design do domínio foi concebido utilizando práticas de **Domain-Driven Design (DDD)** e sessões de **Event Storming** para mapear os fluxos e eventos do sistema, garantindo um alinhamento claro entre as regras de negócio e a implementação técnica.

## 🛠️ Tecnologias e Padrões

Para a construção do backend, utilizamos um conjunto de tecnologias e padrões de mercado que garantem um sistema robusto, seguro e de fácil manutenção:

-   **Linguagem e Framework:** Java 17 e Spring Boot 3.
-   **Segurança:** Autenticação básica gerenciada pelo Spring Security.
-   **Persistência de Dados:** Spring Data JPA com Hibernate.
-   **Banco de Dados:** Suporte a **H2** (para desenvolvimento rápido) e **Microsoft SQL Server** (para ambiente de produção).
-   **Versionamento do Banco:** **Flyway** para gerenciar a evolução do schema do banco de dados de forma automática e segura.
-   **Padrões de Projeto:**
  -   **Repository:** Abstração da camada de acesso aos dados.
  -   **DTO (Data Transfer Object):** Garante que apenas os dados necessários trafeguem entre as camadas e para o cliente.
  -   **Mapper:** Realiza a conversão segura entre Entidades e DTOs, protegendo o modelo interno.
-   **Utilitários:** **Lombok** para reduzir o código boilerplate e aumentar a legibilidade.

## 🏛️ Arquitetura da Solução

O backend foi estruturado utilizando uma **Arquitetura em Camadas**, alinhada aos princípios do padrão MVC, para garantir uma clara separação de responsabilidades:

-   **Controller (Camada de Apresentação):** Recebe as requisições HTTP, valida as entradas e delega a execução para a camada de serviço.
-   **Service (Camada de Negócio):** O "cérebro" da aplicação. Contém toda a lógica e regras de negócio.
-   **Repository (Camada de Persistência):** A única camada responsável por se comunicar com o banco de dados.

A "View" (Visão) em nossa API REST é a representação dos dados entregue ao cliente no formato **JSON**.

Para uma visão detalhada dos fluxos e eventos do sistema, acesse nosso board no Miro:
-   **Miro - Event Storming:** [GastroReserva Board](https://miro.com/app/board/uXjVKOlYiQw=/?share_link_id=285328862468)

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e rodar a aplicação localmente.

### Pré-requisitos
-   Java JDK 17 configurado na máquina (`JAVA_HOME`).
-   Apache Maven.
-   Uma IDE de sua preferência (ex: IntelliJ IDEA, VS Code).
-   (Opcional) Microsoft SQL Server e SSMS para rodar em modo de produção.

### Passo a Passo da Execução
1.  Clone o repositório:
    ```bash
    git clone <url-do-seu-repositorio>
    ```
2.  Abra o projeto na sua IDE. O Maven irá baixar as dependências automaticamente.
3.  **Escolha o Banco de Dados:**
  -   **Para H2 (Padrão):** Nenhuma alteração é necessária. O H2 rodará em memória.
  -   **Para SQL Server:** Siga as instruções na seção "Configurando para SQL Server" abaixo.
4.  Execute a classe principal `GastroReservaApplication.java`. O servidor iniciará na porta `8080`.

### Configurando para SQL Server
1.  **No seu servidor SQL Server,** crie um banco de dados e um login com as credenciais abaixo:
  -   **Banco de Dados:** `bdGastroReserva`
  -   **Login:** `gastro_user`
  -   **Senha:** `gastroreserva123`
2.  **No arquivo `application.properties`,** comente as linhas de configuração do H2 e descomente as linhas para o SQL Server, ajustando a URL do servidor se necessário.
3.  Ao iniciar a aplicação, o **Flyway** criará e populará o banco automaticamente usando os scripts da pasta `db/migration`.

## 📖 Acesso, Teste e Documentação da API

### Autenticação
Todos os endpoints são protegidos por autenticação básica. Para acessar qualquer recurso, forneça as seguintes credenciais:
-   **Username:** `user`
-   **Password:** `password`

### Testando com Postman
A maneira mais recomendada de testar e interagir com a API é através da nossa collection do Postman.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://vitor-acarvalho-8556727.postman.co/workspace/Vitor-Martins-Avelino-de-Carval~7f22442b-6234-4c1f-8889-54e1c5c3ef25/collection/48729584-af3-4ac8-8f3b-7029107f6632?action=share&source=collection_link&creator=48729584)

A collection já vem com a autenticação pré-configurada e está organizada por recurso para facilitar o uso.

> **❗ Atenção:** As URLs na collection podem não incluir o prefixo `/api/v1/`. Se necessário, ajuste as URLs nas requisições do Postman para que os testes funcionem.

### Documentação Interativa (Swagger)
Para uma documentação visual e interativa de todos os endpoints disponíveis:
-   **URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
-   *Lembre-se de clicar no botão "Authorize" e inserir as credenciais para poder testar os endpoints diretamente pela interface.*

### Acesso ao H2 Console
Quando a aplicação está rodando em modo H2, você pode acessar o console do banco de dados para executar queries diretamente:
-   **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
-   **JDBC URL:** `jdbc:h2:mem:gastroDB`
-   **User Name:** `sa`
-   **Password:** (deixe em branco)

## 🤝 Equipe do Projeto
-   LUIS FELIPE
-   JORGE JARDIM
-   VITOR MARTINS AVELINO DE CARVALHO