![Java](https://img.shields.io/badge/-Java-333333?style=flat&logo=Java&logoColor=007396)
![SpringBoot](https://img.shields.io/badge/-Spring%20Boot-333333?style=flat&logo=spring-boot)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/Cadulox/luizalabs-communication-api/blob/main/LICENSE)

[![Build Status](https://app.travis-ci.com/Cadulox/luizalabs-communication-api.svg?branch=main)](https://app.travis-ci.com/Cadulox/luizalabs-communication-api)
[![Coverage Status](https://coveralls.io/repos/github/Cadulox/luizalabs-communication-api/badge.svg?branch=main)](https://coveralls.io/github/Cadulox/luizalabs-communication-api?branch=main)

# Communication API

API desenvolvida com Spring Boot para agendamento de envio de mensagens e persistência dos dados em banco de dados MySQL.

- Tipos de mensagens permitidas para agendamento:
	- EMAIL
	- SMS
	- PUSH
	- WHATSAPP

## Pré-requisitos :exclamation:
- [Java 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [MySQL 5.6](https://www.mysql.com/)
- [Docker](https://www.docker.com/) (opcional)

## Como rodar os testes
- Via linha de comando, navegue até o diretório raiz da aplicação e execute o comando abaixo:
```
$ mvnw clean test
```

## Como rodar a aplicação :arrow_forward:
- Faça o download do projeto ZIP e extraia os arquivos no diretório de sua preferência, ou se preferir clone o projeto com o comando `$ git clone https://github.com/Cadulox/luizalabs-communication-api.git`

- Via linha de comando, navegue até o diretório raiz da aplicação e execute o comando abaixo para criar o jar do projeto:
```
$ mvnw clean package -DskipTests
```
- O arquivo é gerado no diretório target, vá para o diretório com o comando `$ cd target/` e execute o comando abaixo:
```
$ java -jar luizalabs-communication-api-0.0.1-SNAPSHOT.jar
```
:warning: É necessário ter o banco de dados MySQL instalado e rodando na porta padrão `3306`, caso contrário a aplicação falhará ao tentar iniciar.

:warning: A aplicação roda na porta padrão `8080` do Spring Boot.

### Como rodar a aplicação via Docker :whale:
- Caso queira rodar toda aplicação via Docker, navegue através da linha de comando até o diretório raiz da aplicação e execute o comando abaixo:
```
$ docker-compose up
```
- Será feito o download da imagem da aplicação [Communication API](https://hub.docker.com/r/cadulox/luizalabs-communication-api), do banco de dados [MySQL 5.6](https://hub.docker.com/_/mysql), criado os contêineres e aplicação estará rodando perfeitamente.

:warning: A aplicação é exposta na porta padrão 8080 do Spring Boot e o banco de dados na porta padrã `3306` do MySQL.

## Recursos disponíveis da aplicação
Recurso | Verbo HTTP | Path
------- | ---------- | ----
Agendar mensagem | POST | communication-api/schedules
Consultar agendamento | GET | communication-api/schedules/{id}
Cancelar agendamento | PATCH | communication-api/schedules/{id}

- É possível verificar o comportamento dos recursos utilizando [Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/) ou através da documentação da API no link abaixo:

[OpenAPI Doc](http://localhost:8080/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`


## Tecnologias utilizadas :books:
- [Java 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) - como linguagem
- [Spring Boot](https://spring.io/projects/spring-boot) - como framework para criação da API
- [Lombok](https://projectlombok.org/) - para redução de código boilerplate
- [JavaMail API](https://javaee.github.io/javamail/) - para a verificação de emails válidos
- [JUnit5](https://junit.org/junit5/) - como framework para testes unitários
- [AssertJ](https://assertj.github.io/doc/) - biblioteca que fornece um rico conjunto de Assertions
- [Mockito](https://site.mockito.org/) - para instanciar classes e controlar o comportamento dos métodos
- [MySQL](https://www.mysql.com/) - como banco de dados
- [Hibernate](https://hibernate.org/) - para o mapeamento objeto-relacional
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - para facilitar a persistência de dados
- [OpenAPI 3](https://springdoc.org/) - para a geração de documentação
- [Docker](https://www.docker.com/) - para a conteinerização do banco de dados e da aplicação
- [Jib](https://github.com/GoogleContainerTools/jib) - para facilitar a criação de imagens docker

## Desenvolvedores/Contribuintes :octocat:
<table>
  <tr>
    <th> <a href="https://www.linkedin.com/in/carlos-eduardo-lourenco/" target="_blank"> <img src="https://avatars0.githubusercontent.com/u/47247399?s=400&u=7cd0dfdda5675f65a36e1dc75aa8b4ea3343ed98&v=4" width="100"
	alt="Carlos Eduardo"></a> </th>
  </tr>
  <tr>
    <td><h4> Carlos Eduardo </h4></td>
  </tr>  
</table>

## Licença

O Projeto Communication API está licenciado sob a licença The [MIT License](https://opensource.org/licenses/MIT).
