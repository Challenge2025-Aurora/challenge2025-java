# AuroraTrace - Challenge FIAP 2025 - Sprint 1

## Visão Geral

Este projeto é a implementação da primeira sprint do Challenge 2025 da FIAP, envolvendo o módulo de controle de localizações, imagens e monitoramento de motos em pátios da empresa fictícia Mottu.

Foi utilizado Java com Spring Boot, banco de dados H2 em memória e o padrão REST para a exposição dos recursos.

## Tecnologias Utilizadas

- Java 18

- Spring Boot 3.4.5

- Spring Data JPA

- Spring Web

- H2 Database

- Bean Validation

- Swagger/OpenAPI (Springdoc)

## Como Rodar

1. Clonar o repositório

2. Executar o comando:

`./gradlew bootRun`

3. Acessar o Swagger:

`http://localhost:8080/swagger-ui.html`
## Scripts Azure CLI

01) Provisionar uma Máquina Virtual Linux no Azure via CLI
 
az group create -l eastus -n rg-vm-challenge
 
az vm create --resource-group rg-vm-challenge --name vm-challenge --image Canonical:ubuntu-24_04-lts:minimal:24.04.202505020 --size Standard_B2s --admin-username admin_fiap --admin-password admin_fiap@123

02) Abrir as portas necessárias ao projeto via CLI

az network nsg rule create --resource-group rg-vm-challenge --nsg-name vm-challengeNSG --name port_8080 --protocol tcp --priority 1010 --destination-port-range 8080

## Integrantes do Grupo

- Felipe Prometti - RM555174 - 2TDSPM
- Maria Eduarda Pires - RM558976 - 2TDSPZ
- Samuel Damasceno - RM558876 - 2TDSPM

