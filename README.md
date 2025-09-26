<h1 align="center">🌟 SISTEMA DE RESERVA DE RESTAURANTE(GASTRORESERVA)  🌟</h1>

<h2>📋 SUMÁRIO</h2>

- [🔍 Visão Geral](#-VisãoGeral)
- [🏛️ Arquitetura ](#-Arquitetura )
- [🚀 Para startar o projeto](#-Iniciar)
- [📖 Documentação-API](#-Documentação-API)
- [📖 Autenticação-API](#-Autenticação-API)
- [🤝 Integrantes](#-Integrantes)

## 🔍 VisãoGeral
<p>
Projeto apresentado como requisito para conclusão do curso Desenvolvimento FULL STACK, da Universidade CEUB. 
O objetivo do "Sistema de reserva em restaurante" pode ser facilitar e melhorar a experiência de reserva de mesas em restaurantes, tanto para os usuários quanto para os estabelecimentos. Ele pode oferecer uma plataforma centralizada onde os usuários podem gerenciar suas reservas em diferentes restaurantes de forma conveniente e eficiente. 

Além disso, o sistema também ajuda os restaurantes a otimizarem seus serviços, permitindo um melhor planejamento de mesas, gestão de reservas e comunicação com os clientes. Ao proporcionar uma experiência mais fluida e organizada, o objetivo é aumentar a satisfação dos clientes e a eficiência operacional dos restaurantes.
Utilizaremos como ferramenta Domain Driven Design (DDD) para fazer uma implementação baseada em domínio em conjunto com Event Storming onde será apresentado todos os eventos do sistema, para que tanto pessoal de negócio quanto pessoas técnicas possam entender.
Na parte de codificação será utilizado como linguagem de programação JAVA juntamente com o framework spring e seus módulos, tomando como documentação todo o levantamento realizado com Evento Storming.
</p>

## 🏛️ Arquitetura

Em java 17 juntamente com o framework  spring boot entre outros citados nesse durante o projeto onde foi implementado toda a lógica de negócio, utilizamos alguns padrões de projeto como:


- **Autenticação:** autenticação foi realizada de forma basica com spring security.

- **MVC :** onde o projeto é dividido em três camadas: a camada de interação do usuário (view), a camada de manipulação dos dados (model) e a camada de controle (controller).

- **Repository:** para abstrair a camada de acesso ao banco de dados.

- **DTO (Data Transfer Object)** padrão voltado para transferência de dados entre camadas de aplicação.

- **Padrão Mapper:** para transformar DTO´s em entidades e vice-versa.
- **Flyway:** Esse framework é muito utilizado para versionamento e gerenciamento de banco de dados.
- **Lombok:** Que é uma biblioteca que permite deixar o código mais limpo reduzindo o boilerplate por meio de anotações.
- **Banco de Dados h2:** Para vacilitar os testes em qualquer ambiente optamos por utilizar um banco de dados em memória

## 🚀 Iniciar

É necessário ter um java 17 instalado e configurado na maquina e uma IDE para rodar o projeto localmente.

<h2>📖 Acesso ao banco de dados</h2>
Deve ser feito após startar o projeto ja que é um banco de dados configurado para rodar em memória deve ser acessado pela seguinte url contendo essas configurações 
url: http://localhost:8080/h2-console/login.jsp?jsessionid=6959f9445b003b5f86179b413230f210

spring.flyway.url=jdbc:h2:mem:gastroDB
spring.flyway.user=sa
spring.flyway.password=
spring.flyway.locations=classpath:db/migration

## 📖 Documentação-API

Interface Gráfica do Swagger UI:
http://localhost:8080/swagger-ui.html

foi realizado a hospedagem no azure segue link do swagger:
https://ceub-gastro-reserva.azurewebsites.net/swagger-ui/index.html#/
usuario:user
senha:password


- Segue o link do miro aonde mostramos todo os fluxos
https://miro.com/app/board/uXjVKOlYiQw=/?share_link_id=285328862468
- Collection postman: https://universal-crater-6079.postman.co/workspace/ceub~63b45161-f5be-4f39-8e20-86f482f27767/collection/13186621-db46a9b2-2f80-4d99-8331-ee127237e7da?action=share&creator=13186621

## 📖 Autenticação-API
- Autenticação foi configurada pelo spring security de forma basica sendo assim é preciso passar o usuario e senha para poder ter acesso aos end points.
Na aba Authorization - selecionaro type -Basic Auth e informar os dados abaixo
- Username: user
- Password : password


## 🤝 Integrantes

- VITOR MARTINS AVELINO DE CARVALHO

