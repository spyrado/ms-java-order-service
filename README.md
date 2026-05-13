# ms-java-order-service

Microserviço responsável pelo gerenciamento de pedidos do sistema e-commerce.

## Tecnologias
- Java 21
- Spring Boot 4.0.6
- Spring Data JPA + Hibernate
- PostgreSQL
- Apache Kafka (producer)
- Maven

## Pré-requisitos
- Java 21 instalado
- infra-local rodando (`docker-compose up`)

## Como rodar
```bash
./mvnw spring-boot:run
```

## Porta
Roda na porta `8081`

## Endpoints
| Método | Rota | Descrição |
|---|---|---|
| POST | /orders | Criar pedido |
| GET | /orders | Listar pedidos |
| GET | /orders/{id} | Buscar pedido por ID |
| PUT | /orders/{id} | Atualizar pedido |
| DELETE | /orders/{id} | Deletar pedido |