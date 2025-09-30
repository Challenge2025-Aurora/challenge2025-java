# AuroraTrace - Challenge FIAP 2025

---

## Visão Geral da Solução

Nossa solução é uma aplicação completa para gerenciar as motos dentro dos pátios da Mottu, controlando em que setor elas estão, seus status (Disponível, Manutenção, Ocupada) e seus eventos de entrada/saída.

A solução é composta por três repositórios principais:

| Repositório | Tecnologia | URL                                                                                   |
| :--- | :--- |:--------------------------------------------------------------------------------------|
| **Mobile App** | React Native/Expo | [Mobile](https://github.com/Challenge2025-Aurora/aurora-mobile)                       |
| **API Java** | Spring Boot | [Java](https://github.com/Challenge2025-Aurora/challenge2025-java) (Este Repositório) |
| **API C#** | .NET Core | [C#](https://github.com/Challenge2025-Aurora/aurora-cs)                               |

## Integrantes do Grupo

- **Felipe Prometti** - RM555174 - 2TDSPM
- **Maria Eduarda Pires** - RM558976 - 2TDSPZ
- **Samuel Damasceno** - RM558876 - 2TDSPM

## Tecnologias Usadas (Java)

| Categoria | Tecnologia                  |
| :--- |:----------------------------|
| **Linguagem** | Java 18                     |
| **Framework** | Spring Boot 3               |
| **Persistência** | Spring Data JPA / Hibernate |
| **Banco de Dados** | H2 Database                 |
| **Migração** | Flyway                      |
| **Frontend Web** | Thymeleaf                   |
| **Segurança** | Spring Security             ||
| **Documentação** | Swagger                     |
| **Validação** | Bean Validation             |

## Como Rodar o Projeto

### Pré-requisitos
- Java 18 JDK instalado.
- Gradle instalado

1. **Clonar o Repositório:**
   ```bash
   git clone [https://github.com/Challenge2025-Aurora/challenge2025-java.git](https://github.com/Challenge2025-Aurora/challenge2025-java.git)
   cd challenge2025-java
   ```
   
2. **Executar a Aplicação:**
    ```bash
   ./gradlew bootRun
    ```

3. O banco de dados H2 será inicializado automaticamente pelo Flyway, populando as tabelas e dados.

| Recurso                | Endpoint                              |
|:-----------------------|:--------------------------------------|
| Página principal       | http://localhost:8080/                |
| Página de autenticação | http://localhost:8080/login           |
| Documentação da API    | http://localhost:8080/swagger-ui.html |

4. Para passar da página de login, você pode usar as credenciais abaixo:

| Perfil | Email | Senha     |
| :--- | :--- |:----------|
| **ADMIN** | `admin@fiap.com` | `fiap123` |
| **OPERADOR** | `operador@fiap.com` | `fiap123` |

---

## Scripts Azure CLI

01) Provisionar um Grupo de Recursos no Azure:
```bash
- az group create -l eastus -n rg-vm-challenge
```

02) Provisionar uma Máquina Virtual Linux:
```bash
- az vm create --resource-group rg-vm-challenge --name vm-challenge --image Canonical:ubuntu-24_04-lts:minimal:24.04.202505020 --size Standard_B2s --admin-username admin_fiap --admin-password admin_fiap@123
```

3) Abrir as portas necessárias ao projeto via CLI:

```bash
- az network nsg rule create --resource-group rg-vm-challenge --nsg-name vm-challengeNSG --name port_8080 --protocol tcp --priority 1010 --destination-port-range 8080
```