# 👥 API Gestão de Pessoas

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)

> Uma API RESTful robusta para gerenciamento de pessoas, desenvolvida com foco em **Clean Code**, **Performance** e **Arquitetura de Software**.

---

## 🚀 Sobre o Projeto

Este projeto não é apenas um CRUD. É uma implementação de referência demonstrando como construir microsserviços modernos utilizando o ecossistema Spring. 

O foco principal foi a aplicação de boas práticas de engenharia de software, incluindo a separação estrita de responsabilidades (DTO Pattern), versionamento de banco de dados e testes automatizados.

### 🎯 Destaques Técnicos
* **Performance:** Uso de **MapStruct** para conversão de objetos (DTO <-> Entity) em tempo de compilação, evitando a lentidão do Reflection.
* **Segurança & Design:** Separação entre `RequestDTO` (Entrada) e `ResponseDTO` (Saída) para proteger dados sensíveis.
* **Confiabilidade:** Versionamento de banco de dados com **Flyway Migrations**.
* **Qualidade:** Pipeline de CI/CD configurado com **GitHub Actions**.

---

## 🛠️ Tech Stack

* **Linguagem:** Java 17 (LTS)
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL (Produção) / H2 (Testes em Memória)
* **Gerenciamento de Dados:** Spring Data JPA
* **Migrations:** Flyway
* **Mapeamento de Objetos:** MapStruct
* **Redução de Boilerplate:** Lombok
* **Containerização:** Docker

---

## ⚙️ Como Executar

### Pré-requisitos
* Java 17 instalado
* Docker (Opcional, mas recomendado)
* Maven

### 🐳 Opção 1: Via Docker (Recomendado)
Se você tiver o Docker instalado, basta rodar o comando na raiz para subir o banco e a aplicação:

```bash
docker-compose up -d
