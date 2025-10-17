# 🏭 Estoque Eficiente - Controle de Estoque para Pequenas Fábricas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Status](https://img.shields.io/badge/Status-Backend%20Ativo-brightgreen?style=for-the-badge)

### Transformando experiência de chão de fábrica em um software que previne perdas e otimiza a produção.

---

## 🎯 O Problema Real

Este projeto não nasceu de uma ideia acadêmica, mas da realidade do chão de fábrica. Tendo trabalhado em pequenos comércios e fábricas de produção própria, onde as margens de lucro são apertadas, testemunhei em primeira mão como um simples erro de comunicação ou uma contagem de estoque imprecisa pode se transformar em um grande prejuízo.

Uma produção parada por falta de matéria-prima ou um pedido atrasado por falta de um componente são problemas reais que afetam diretamente o faturamento e a credibilidade do negócio. A falta de uma ferramenta simples e focada na **relação entre o estoque e o fluxo de produção** é uma dor constante para o pequeno produtor.

O **Estoque Eficiente** nasceu para resolver essa dor.

## ✨ A Solução

Um sistema de controle de estoque focado não apenas em *quantos* itens existem, mas em *como* eles se conectam no fluxo de produção. O objetivo é criar uma ponte de comunicação clara entre o estoque de matérias-primas, as ordens de produção e o estoque de produtos acabados, fornecendo previsibilidade e evitando rupturas.

## 🚀 Funcionalidades Principais (Backend)

* **📦 Cadastro de Itens:** Gerenciamento de Produtos Acabados e Matérias-Primas.
* **📋 Gestão de Ficha Técnica:** Definição da "receita" de cada produto, especificando quais componentes e quantidades são necessários.
* **✅ Verificação de Viabilidade de Produção:** Antes de iniciar uma ordem de produção, o sistema verifica automaticamente se há matéria-prima suficiente em estoque.
* **📊 Persistência de Dados:** Todas as informações são salvas de forma segura em um banco de dados relacional.

## 🛠️ Status e Evolução do Projeto

### Status Atual
O projeto foi migrado com sucesso para uma arquitetura robusta com **Spring Boot**. O backend agora se conecta a um banco de dados **PostgreSQL**, e o **Spring Data JPA / Hibernate** gerencia automaticamente a criação do schema das tabelas. A base para a construção de uma API REST está pronta.

### Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Framework:** Spring Boot
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Build Tool:** Maven

### Roadmap (Próximos Passos)
- [x] Modelagem do Domínio (Produtos, Ficha Técnica)
- [x] ~~Implementação dos Repositórios em Memória~~ (Evoluído)
- [x] ~~Criação dos Serviços de Estoque e Produção~~ (Refatorado para Spring)
- [x] ~~Implementar a lógica de baixa de estoque e finalização de produção.~~ (Refatorado para Spring)
- [x] **Migração do projeto para Spring Boot.**
- [x] **Integração com Banco de Dados (PostgreSQL) usando JPA/Hibernate.**
- [ ] **Construção de uma API REST com Spring Boot para expor as funcionalidades.**
- [ ] Implementação de Testes Unitários e de Integração.
- [ ] Implantação em um serviço de Cloud.
- [ ] Desenvolvimento da interface Frontend.

---

## 🚀 Evolução Arquitetural: De Simulação para Aplicação Real

A atualização mais recente representa a maior evolução do projeto até o momento, transformando a prova de conceito em uma aplicação backend profissional e persistente.

* **Migração para Spring Boot:** O projeto foi completamente reestruturado para utilizar o ecossistema Spring, aproveitando a injeção de dependências, a autoconfiguração e a robustez do framework padrão do mercado.

* **Persistência com PostgreSQL:** A "memória volátil" foi substituída por um banco de dados PostgreSQL. Todos os dados de produtos e fichas técnicas agora são permanentes.

* **Mapeamento Objeto-Relacional com JPA/Hibernate:** As classes de modelo (`Produto`, `FichaTecnica`) foram mapeadas como entidades (`@Entity`). O Hibernate agora gerencia a criação e atualização automática das tabelas no banco de dados, eliminando a necessidade de SQL manual.

* **Repositórios Superpoderosos com Spring Data JPA:** As interfaces de repositório agora estendem `JpaRepository`, ganhando métodos de acesso a dados (CRUD) prontos para uso e a capacidade de criar buscas complexas apenas pela nomenclatura dos métodos, como `findByProdutoAcabadoId`.

Essa transformação estabelece a base sólida para a próxima grande fase: a construção da API REST.

---

## ✍️ Autor

* **[João Victor Vaz de Queiroz]** - [LinkedIn](https://www.linkedin.com/in/joaovictorvazdeq/)
