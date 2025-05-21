# 🏍️ Solução Pulse – Backend Java

## Índice

- [Funcionalidades da Aplicação Java](#funcionalidades-da-aplicação-java)
- [Endpoints de Funcionamento das Entidades](#endpoints-de-funcionamento-das-entidades)
  - [Moto](#moto)
  - [Patio](#patio)
  - [Beacon](#beacon)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Rodar a API](#como-rodar-a-api)
- [Dockerfile](#dockerfile)
- [Uso do Azure CLI para provisionamento da aplicação](#uso-do-azure-cli-para-provisionamento-da-aplicação)
- [Grupo Desenvolvedor](#👥-grupo-desenvolvedor)

---

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


## Como Rodar a API

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


## Dockerfile
O projeto conta com a inserção de um Dockerfile em sua raiz para a criação da imagem da aplicação.

### Como rodar o DockerFile
```bash
docker build -t [nome-da-imagem]:[versao-da-imagem] .
```

## Uso do Azure CLI para provisionamento da aplicação
Para a estruturação e criação de uma Máquina Virtual Linux na Azure responsável por rodar o Container com a imagem da aplicação desenvolvida, utiliza-se 
os seguintes comandos:

### Criação do Resource Group
```bash
az group create -l eastus -n rg-vm-pulse
```

### Criação da Virtual Machine
```bash
az vm create --resource-group rg-vm-pulse --name vm-pulse --image Canonical:ubuntu-24_04-lts:minimal:24.04.202505020
--size Standard_B2s --admin-username admin_pulse --admin-password pulse_system@123
```

### Instalação do Docker na VM
```bash
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo docker run hello-world
```

### Inserção de Network Security Group para abertura de conexão
```bash
az network nsg rule create --resource-group rg-vm-challenge --nsg-name vm-challengeNSG --name port_8080 --protocol tcp --priority 1010 --destination-port-range 8080
```
```bash
az network nsg rule create --resource-group rg-vm-challenge --nsg-name vm-challengeNSG --name port_80 --protocol tcp --priority 1020 --destination-port-range 80
```

### Criação de Container para rodar imagem
```bash
docker run -d -p 8080:80 gabrielasreiss/pulse_system:1.0
```

## 👥 Grupo Desenvolvedor 
- Gabriela de Sousa Reis - RM558830
- Laura Amadeu Soares - RM556690
- Raphael Lamaison Kim - RM557914


