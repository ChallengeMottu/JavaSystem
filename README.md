# 🏍️ Solução Pulse – Backend Java

A **Solução Pulse** é uma proposta para otimizar a gestão dos pátios da empresa **Mottu**, responsável pela alocação e controle de motos. A identificação de cada moto é feita por meio da tecnologia **Beacon BLE**, permitindo rastreabilidade em tempo real.

Embora o sistema Java **não receba diretamente os sinais BLE**, ele é responsável por **gerenciar o cadastro e a associação entre motos e seus respectivos beacons**, garantindo a identificação precisa a partir do código BLE detectado por dispositivos externos (como scanners BLE).

---

## Funcionalidades da Aplicação Java

A aplicação foi desenvolvida com **Java + Spring Boot** e segue uma arquitetura em camadas, oferecendo os seguintes recursos principais:

- 📌 **Cadastro e edição de motos**, incluindo dados como modelo, status e condição mecânica.
- 📡 **Cadastro de beacons BLE**, com identificador único.
- 🔗 **Associação entre motos e beacons**, permitindo a identificação automatizada via código BLE.
- 🧭 **Sugestão de alocação no pátio**, com base na condição mecânica e status de uso da moto.
- 📊 **Consulta de motos** por ID, status, zona ou código BLE.
- 🗺️ **Gestão das zonas do pátio**, com base em espaço disponível e tipo de moto.

---

## Endpoints de Funcionamento das Entidades 
# Moto

| Método | Endpoint               | Descrição                              |
|--------|------------------------|--------------------------------------|
| GET    | `/moto/{id}`           | Busca Moto por ID                     |
| GET    | `/moto`                | Lista todas as Motos                  |
| GET    | `/moto/pageable`       | Lista Motos paginadas                 |
| POST   | `/moto/{patioId}`      | Cadastra Moto vinculado a Pátio      |
| PUT    | `/moto/{id}`           | Atualiza Moto por ID                  |
| DELETE | `/moto/{id}`           | Deleta Moto por ID                    |
| GET    | `/moto/beacon/{codigo}`| Busca Moto pelo código UUID do Beacon|

# Patio

| Método | Endpoint              | Descrição                          |
|--------|-----------------------|----------------------------------|
| GET    | `/patio`              | Lista todos os Pátios             |
| GET    | `/patio/pageable`     | Lista Pátios paginados            |
| GET    | `/patio/{id}`         | Busca Pátio por ID               |
| GET    | `/patio/{id}/motos`   | Lista Motos de um Pátio           |
| POST   | `/patio`              | Cadastra novo Pátio              |
| PUT    | `/patio/{id}`         | Atualiza Pátio por ID             |
| DELETE | `/patio/{id}`         | Deleta Pátio por ID               |

# Beacon
| Método | Endpoint           | Descrição                           |
|--------|--------------------|-----------------------------------|
| GET    | `/beacon/{id}`     | Busca Beacon por ID                |
| GET    | `/beacon`          | Lista todos os Beacons             |
| GET    | `/beacon/pageable` | Lista Beacons paginados            |
| POST   | `/beacon/{motoId}` | Cadastra Beacon vinculado a Moto  |
| PUT    | `/beacon/{id}`     | Atualiza Beacon por ID             |
| DELETE | `/beacon/{id}`     | Deleta Beacon por ID               |


## Tecnologias Utilizadas
- Java 17  
- Spring Boot 3.4.4  
- Spring Data JPA  
- Spring Boot Starter Web  
- Springdoc OpenAPI (Swagger UI)  
- H2 Database (banco em memória para desenvolvimento)


## Como Rodar o Projeto

1. Clone o repositório:  
```bash
git clone https://github.com/seu-usuario/pulse-api.git](https://github.com/ChallengeMottu/JavaSystem.git
```

2. Compile e rode a aplicação usando Maven
```bash
mvn spring-boot:run
```

3. Após a aplicação iniciar, acesse a documentação da API pelo navegador em:
```bash
http://localhost:8080/swagger-ui.html
```

4. Para acessar o console do H2, acesse:
```bash
http://localhost:8080/h2-console
```
