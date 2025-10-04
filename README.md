
# 🏭 Estoque Eficiente - Controle de Estoque para Pequenas Fábricas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)

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
* **📊 Rastreabilidade de Estoque:** (Em breve) Histórico completo de todas as movimentações de entrada e saída.

## 🛠️ Tecnologias

* **Backend:** Java
* **Banco de Dados:** (Futuro) PostgreSQL
* **Framework:** (Futuro) Spring Boot para a API REST
* **Cloud:** (Futuro) Implantação na AWS, GCP ou Azure
* **Frontend:** (Futuro) React, Vue ou Angular

## 📈 Status do Projeto

O projeto está atualmente na fase de desenvolvimento do **backend core em Java puro**. A arquitetura está sendo construída em camadas (Modelo, Repositório, Serviço) para garantir manutenibilidade e escalabilidade.

### Roadmap (Próximos Passos)
- [x] Modelagem do Domínio (Produtos, Ficha Técnica)
- [x] Implementação dos Repositórios em Memória
- [x] Criação dos Serviços de Estoque e Produção
- [x] Implementar a lógica de baixa de estoque e finalização de produção.
- [ ] Integração com um Banco de Dados (PostgreSQL) usando JPA/Hibernate.
- [ ] Construção de uma API REST com Spring Boot.
- [ ] Implantação em um serviço de Cloud.
- [ ] Desenvolvimento da interface Frontend.


---

## 🏆 Marco Atingido: Lógica de Negócio Concluída! (Outubro de 2025)

A primeira grande fase do projeto foi concluída com sucesso! Todo o "cérebro" do sistema, contendo a lógica de negócio para controle de estoque e simulação de produção, está **100% funcional em memória**.

Isso significa que o sistema já é capaz de simular e gerenciar o fluxo de produção de ponta a ponta, validando a arquitetura e as regras de negócio antes mesmo da implementação do banco de dados e da interface de usuário.

#### ✨ Últimas Adições e Funcionalidades Implementadas:

* **Arquitetura em 3 Camadas:** O projeto foi estruturado de forma profissional, separando as responsabilidades:
    * **Modelo:** Define as entidades do negócio (`Produto`, `FichaTecnica`, etc.).
    * **Repositório:** Abstrai o acesso aos dados (atualmente em memória, pronto para o BD).
    * **Serviço:** Orquestra as regras de negócio complexas.

* **Serviço de Estoque (`EstoqueService`):**
    * Responsável por todas as operações de estoque, como cadastrar novos produtos, registrar entradas e saídas.

* **Serviço de Produção (`ProducaoService`):**
    * **Verificação de Viabilidade:** O sistema consegue analisar a "receita" (`FichaTecnica`) de um produto e verificar se há matéria-prima suficiente em estoque para atender a uma ordem de produção.
    * **Execução da Produção:** Após a verificação, o serviço executa a ordem, dando baixa automática no estoque das matérias-primas consumidas e adicionando o produto final ao estoque.

* **Simulação Completa:** Foi criado um script de teste (`Main.java`) que valida todo o fluxo, simulando cenários de sucesso (produção viável) e de falha (estoque insuficiente), garantindo a robustez da lógica implementada.

---



## ✍️ Autor



* **[João Victor Vaz de Queiroz]** - https://www.linkedin.com/in/joaovictorvazdeq/

```



