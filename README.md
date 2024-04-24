# Simulação e Contratação de empréstimo consignado

Esse repositório contém um sistema em uma arquitetura de microsserviços desacoplados que proporciona a experiência de simular e efetuar contratações de empréstimo consignado.

## Tecnologias usadas

- Java
- Spring Boot
- Gradle

## Pré requisitos

Para rodar esse projeto, você vai precisar:

- JDK 17
- Git

## Como rodar?

1. Clone esse repositório em sua máquina local:

    ```bash
    git clone https://github.com/MatheusTabarelli/projeto-consignado.git
    ```

2. Abra os 3 microsserviços (customer-register, simulation, custody) na sua IDE de preferência.

3. Use o Insomnia ou qualquer outro REST client para fazer chamadas HTTP para os microsserviços. Há uma collection do Insomnia nesse repositório para facilmente testar as funcionalidades do projeto.

## Arquitetura

Há também um desenho de arquitetura TO BE no ambiente AWS, feito pensando em um contexto bancário.
