# 🏦 Zenón Bank - Projeto Prático: Fundamentos Java

Bem-vindo ao repositório base do projeto prático da disciplina de Fundamentos Java. Este projeto simula o backend de uma fintech fictícia, o **Zenón Bank**, onde você aplicará conceitos desde manipulação de tipos básicos até processamento de alta performance e concorrência.

## 📋 Sobre o Projeto

O objetivo é consolidar os conhecimentos adquiridos em aula através de um cenário realista. Você lidará com:
* Modelagem de dados com **Records**.
* API de Collections e Streams.
* Manipulação de I/O de arquivos grandes.
* Tratamento de Exceções.
* Integração com Banco de Dados.
* Concorrência e Paralelismo.

Siga o passo a passo do quadro de atividades (Trello) do projeto prático dessa disciplina, disponível nos vídeos de projeto prático da disciplina.

## 📦 Requisitos

* **Git e GitHub**: para controle de versões e portifólio de repositórios.
* **Java JDK**: 25 (LTS).
* **IDE**: IntelliJ IDEA ou Eclipse.
* **Docker e Docker Compose**: Para a tarefa de Banco de Dados.


## 📦 Alguns números

* **Teste de leitura com Stream das 6,362,620 de transações**: mediana de 3,055 s
* **Teste de leitura com While das 6,362,620 de transações**: mediana de 2,8515 s

Com quatro medições:
Versão	Tempos em ordem crescente (s)	Mediana
while	2,772 · 2,831 · 2,872 · 2,874	2,8515 s
Stream	2,937 · 3,055 · 3,055 · 3,146	3,055 s


Nessas medições, o while levou **203,5** ms a menos na mediana, uma redução de **6,7%** em relação à Stream.
