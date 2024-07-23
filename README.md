# FIAP - E-COMMERCE - FASE 05

Este projeto foi elaborado cumprindo os objetivos da FASE 05 Pós Arquitetura e Desenvolvimento Java da FIAP.

# Objetivos

Desenvolver um  sistema e-commerce com as seguintes funcionalidades:

- Login e Registro de Usuários;
- Gestão de Itens;
- Carrinho de compras;
- Pagamentos;

# Documentação da API


# Gerenciamento de Clientes

## **GETs**

### **Listar Clientes**
- **URL:** `GET localhost:8082/clientes/clientes`
- **Descrição:** Retorna uma lista de todos os clientes.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/clientes/clientes'
  ```

### **Buscar CEP Cliente**
- **URL:** `GET localhost:8082/clientes/clientes/buscar-cep/{id}`
- **Parâmetros:**
    - `id` (number, required): ID do cliente.
- **Descrição:** Busca o CEP de um cliente específico.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/clientes/clientes/buscar-cep/3'
  ```

### **Buscar Cliente pelo CPF**
- **URL:** `GET localhost:8082/clientes/clientes/filtro?cpf={cpf}`
- **Parâmetros:**
    - `cpf` (string, required): CPF do cliente.
- **Descrição:** Busca um cliente específico pelo CPF.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/clientes/clientes/filtro?cpf=555'
  ```

## **POSTs**

### **Criar Cliente**
- **URL:** `POST localhost:8082/clientes/clientes/criar-cliente`
- **Descrição:** Cria um novo cliente.
- **Corpo da Requisição:**
  ```json
  {
      "nome": "Matheus Hajer",
      "cpf": "14475068045",
      "email": "email@gmail.com",
      "dadosCriacaoEnderecoDTO": [
          {
              "cep": "1310100",
              "logradouro": "Rua Exemplo",
              "numero": "321",
              "complemento": "Apto 456",
              "bairro": "Centro",
              "cidade": "Carapicuiba",
              "uf": "SP",
              "isEnderecoPrincipal": true
          }
      ],
      "dadosCriacaoTelefoneDTO": [
          {
              "ddi": "55",
              "ddd": "11",
              "numero": "940090003",
              "isTelefonePrincipal": true
          }
      ]
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/clientes/clientes/criar-cliente'   --header 'Content-Type: application/json'   --data-raw '{
      "nome": "Matheus Hajer",
      "cpf": "14475068045",
      "email": "email@gmail.com",
      "dadosCriacaoEnderecoDTO": [
          {
              "cep": "1310100",
              "logradouro": "Rua Exemplo",
              "numero": "321",
              "complemento": "Apto 456",
              "bairro": "Centro",
              "cidade": "Carapicuiba",
              "uf": "SP",
              "isEnderecoPrincipal": true
          }
      ],
      "dadosCriacaoTelefoneDTO": [
          {
              "ddi": "55",
              "ddd": "11",
              "numero": "940090003",
              "isTelefonePrincipal": true
          }
      ]
  }'
  ```

### **Adicionar Telefone**
- **URL:** `POST localhost:8082/clientes/telefones/{id}/adicionar-telefone`
- **Parâmetros:**
    - `id` (number, required): ID do cliente.
- **Corpo da Requisição:**
  ```json
  [
      {
          "ddi": 19,
          "ddd": 19,
          "numero": 98946789
      }
  ]
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/clientes/telefones/2/adicionar-telefone'   --header 'Content-Type: application/json'   --data-raw '[
      {
          "ddi": 19,
          "ddd": 19,
          "numero": 98946789
      }
  ]'
  ```

### **Adicionar Endereço**
- **URL:** `POST localhost:8082/clientes/enderecos/{id}/adicionar-endereco`
- **Parâmetros:**
    - `id` (number, required): ID do cliente.
- **Corpo da Requisição:**
  ```json
  [
      {
          "cep": "69037-590",
          "logradouro": "Beco Major Natan",
          "numero": "123",
          "complemento": "",
          "bairro": "Lírio do Vale",
          "cidade": "Manaus",
          "uf": "AM",
          "isTelefonePrincipal": true
      }
  ]
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/clientes/enderecos/2/adicionar-endereco'   --header 'Content-Type: application/json'   --data-raw '[
      {
          "cep": "69037-590",
          "logradouro": "Beco Major Natan",
          "numero": "123",
          "complemento": "",
          "bairro": "Lírio do Vale",
          "cidade": "Manaus",
          "uf": "AM",
          "isTelefonePrincipal": true
      }
  ]'
  ```

## **DELETEs**

### **Deletar Telefone**
- **URL:** `DELETE localhost:8082/clientes/telefones/{id}`
- **Parâmetros:**
    - `id` (number, required): ID do telefone.
- **Exemplo:**
  ```bash
  curl --location --request DELETE 'localhost:8082/clientes/telefones/1'
  ```

### **Deletar Endereço**
- **URL:** `DELETE localhost:8082/clientes/enderecos/{id}`
- **Parâmetros:**
    - `id` (number, required): ID do endereço.
- **Exemplo:**
  ```bash
  curl --location --request DELETE 'localhost:8082/clientes/enderecos/3'
  ```

## **PUTs**

### **Atualizar Telefone Principal**
- **URL:** `PUT localhost:8082/clientes/telefones/{id}/atualizar-telefone-principal`
- **Parâmetros:**
    - `id` (number, required): ID do telefone.
- **Corpo da Requisição:**
  ```json
  {
      "ddi": 19,
      "ddd": 19,
      "numero": 34578509
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PUT 'localhost:8082/clientes/telefones/1/atualizar-telefone-principal'   --header 'Content-Type: application/json'   --data-raw '{
      "ddi": 19,
      "ddd": 19,
      "numero": 34578509
  }'
  ```

### **Atualizar Endereço Principal**
- **URL:** `PUT localhost:8082/clientes/enderecos/{id}/atualizar-endereco-principal`
- **Parâmetros:**
    - `id` (number, required): ID do endereço.
- **Corpo da Requisição:**
  ```json
  {
      "cep": "69037-590",
      "logradouro": "Beco Major Natan",
      "numero": "123",
      "complemento": "",
      "bairro": "Lírio do Vale",
      "cidade": "Manaus",
      "uf": "AM"
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PUT 'localhost:8082/clientes/enderecos/1/atualizar-endereco-principal'   --header 'Content-Type: application/json'   --data-raw '{
      "cep": "69037-590",
      "logradouro": "Beco Major Natan",
      "numero": "123",
      "complemento": "",
      "bairro": "Lírio do Vale",
      "cidade": "Manaus",
      "uf": "AM"
  }'
  ```

## **PATCHs**

### **Atualizar Nome do Cliente**
- **URL:** `PATCH localhost:8082/clientes/clientes/{id}/atualizar-nome`
- **Parâmetros:**
    - `id` (number, required): ID do cliente.
- **Corpo da Requisição:**
  ```json
  {
      "nomeAtualizado": "Cliente Teste Atualizado"
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PATCH 'localhost:8082/clientes/clientes/2/atualizar-nome'   --header 'Content-Type: application/json'   --data-raw '{
      "nomeAtualizado": "Cliente Teste Atualizado"
  }'
  ```

# Gerenciamento de Produtos

## **GETs**

### **Listar Todos Produtos Paginado**
- **URL:** `GET localhost:8082/produtos/produtos`
- **Descrição:** Retorna uma lista paginada de todos os produtos.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos'
  ```

### **Buscar Produto por Id**
- **URL:** `GET localhost:8082/produtos/produtos/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do produto.
- **Descrição:** Busca um produto específico pelo ID.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/5'
  ```

### **Buscar Produto por Nome**
- **URL:** `GET localhost:8082/produtos/produtos/listar-por-nome/{nome}`
- **Parâmetros:**
  - `nome` (string, required): Nome do produto.
- **Descrição:** Busca um produto específico pelo nome.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/listar-por-nome/Teclado'
  ```

### **Buscar Produto por Categoria**
- **URL:** `GET localhost:8082/produtos/produtos/listar-por-categoria/{categoria}`
- **Parâmetros:**
  - `categoria` (string, required): Categoria do produto.
- **Descrição:** Busca produtos por categoria.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/listar-por-categoria/INFORMATICA'
  ```

## **POSTs**

### **Criar Produto**
- **URL:** `POST localhost:8082/produtos/produtos/criar-produto`
- **Descrição:** Cria um novo produto.
- **Corpo da Requisição:**
  ```json
  {
      "nome": "Teclado",
      "descricao": "Teclado semi-mecânico, para uso diario.",
      "preco": 200.0,
      "quantidade": 100,
      "categoria": "INFORMATICA",
      "altura": 3,
      "largura": 15,
      "comprimento": 30,
      "peso": 0.3
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/produtos/produtos/criar-produto'   --header 'Content-Type: application/json'   --data-raw '{
      "nome": "Teclado",
      "descricao": "Teclado semi-mecânico, para uso diario.",
      "preco": 200.0,
      "quantidade": 100,
      "categoria": "INFORMATICA",
      "altura": 3,
      "largura": 15,
      "comprimento": 30,
      "peso": 0.3
  }'
  ```

### **Importar Arquivo CSV**
- **URL:** `POST localhost:8082/produtos/produtos/importar-csv`
- **Descrição:** Importa produtos a partir de um arquivo CSV.
- **Corpo da Requisição:**
  ```json
  {
      "file": "path/to/your/csvfile.csv"
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/produtos/produtos/importar-csv'   --form 'file=@/path/to/your/csvfile.csv'
  ```

## **PATCHs**

### **Atualizar Produto por Id**
- **URL:** `PATCH localhost:8082/produtos/produtos/atualizar-produto/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do produto.
- **Corpo da Requisição:**
  ```json
  {
      "quantidade": 120
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PATCH 'localhost:8082/produtos/produtos/atualizar-produto/2'   --header 'Content-Type: application/json'   --data-raw '{
      "quantidade": 120
  }'
  ```

## **DELETEs**

### **Deletar Produto por Id**
- **URL:** `DELETE localhost:8082/produtos/produtos/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do produto.
- **Exemplo:**
  ```bash
  curl --location --request DELETE 'localhost:8082/produtos/produtos/1'
  ```

# Gerenciamento de Pedidos

## **POSTs**

### **Criar Pedido**
- **URL:** `POST localhost:8082/pedidos/pedidos/criar-pedido`
- **Descrição:** Cria um novo pedido.
- **Corpo da Requisição:**
  ```json
  {
      "clienteId": 4,
      "itensPedido": [
          {
              "produtoId": 1,
              "quantidade": 1
          },
          {
              "produtoId": 2,
              "quantidade": 10
          }
      ]
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/pedidos/pedidos/criar-pedido'   --header 'Content-Type: application/json'   --data-raw '{
      "clienteId": 4,
      "itensPedido": [
          {
              "produtoId": 1,
              "quantidade": 1
          },
          {
              "produtoId": 2,
              "quantidade": 10
          }
      ]
  }'
  ```

### **Inserir Dados de Entrega**
- **URL:** `PATCH localhost:8082/pedidos/pedidos/inserir-dados-entrega/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do pedido.
- **Descrição:** Insere dados de entrega em um pedido.
- **Corpo da Requisição:**
  ```json
  {
      "prazoEntrega": 3,
      "frete": 39.50,
      "codigoDeRastreio": 12345678
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PATCH 'localhost:8082/pedidos/pedidos/inserir-dados-entrega/8'   --header 'Content-Type: application/json'   --data-raw '{
      "prazoEntrega": 3,
      "frete": 39.50,
      "codigoDeRastreio": 12345678
  }'
  ```

### **Atualizar Status do Pedido**
- **URL:** `PATCH localhost:8082/pedidos/pedidos/atualizar-status-pedido/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do pedido.
- **Descrição:** Atualiza o status de um pedido.
- **Corpo da Requisição:**
  ```json
  {
      "status": "PAGO"
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request PATCH 'localhost:8082/pedidos/pedidos/atualizar-status-pedido/25'   --header 'Content-Type: application/json'   --data-raw '{
      "status": "PAGO"
  }'
  ```

## **APIs Para Outros Microservices**

### **Buscar Dados Para Pedido**
- **URL:** `GET localhost:8082/produtos/produtos/dados-pedido/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do pedido.
- **Descrição:** Busca dados necessários para um pedido.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/dados-pedido/4'
  ```

### **Buscar Dados Para Entrega**
- **URL:** `GET localhost:8082/produtos/produtos/dados-entrega/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do pedido.
- **Descrição:** Busca dados necessários para a entrega de um pedido.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/dados-entrega/4'
  ```

### **Salvar o usuário da base de produtos**

- **URL:** `POST http://localhost:8082/produtos/user/`
- **Descrição:** Cria informações de login na database de produtos.
- **Corpo da Requisição:**
  ```json
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "login": "exampleLogin",
    "password": "examplePassword",
    "roles": [
        "USER",
        "ADMIN"
    ]
  }

- **Exemplo:**
  ```bash
     curl --location --request POST 'localhost:8082/usuarios/criar-usuario' \
     --header 'Content-Type: application/json' \
     --data-raw '{
        "id": "123e4567-e89b-12d3-a456-426614174000",
        "login": "exampleLogin",
        "password": "examplePassword",
        "roles": [
            "USER",
            "ADMIN"
    ]
  }'


# Gerenciamento de Entregas

## **POSTs**

### **Criar Entrega**
- **URL:** `POST localhost:8082/entregas/entregas/criar-entrega`
- **Descrição:** Cria uma nova entrega.
- **Corpo da Requisição:**
  ```json
  {
      "pedido_id": 1,
      "cliente_id": 4,
      "produtos": [
          {
              "produto_id": "1",
              "quantidade": 1
          },
          {
              "produto_id": "2",
              "quantidade": 10
          }
      ]
  }
  ```
- **Exemplo:**
  ```bash
  curl --location --request POST 'localhost:8082/entregas/entregas/criar-entrega'   --header 'Content-Type: application/json'   --data-raw '{
      "pedido_id": 1,
      "cliente_id": 4,
      "produtos": [
          {
              "produto_id": "1",
              "quantidade": 1
          },
          {
              "produto_id": "2",
              "quantidade": 10
          }
      ]
  }'
  ```

## **APIs Para Outros Microservices**

### **Buscar Dados Para Entrega**
- **URL:** `GET localhost:8082/produtos/produtos/dados-entrega/{id}`
- **Parâmetros:**
  - `id` (number, required): ID do pedido.
- **Descrição:** Busca dados necessários para a entrega de um pedido.
- **Exemplo:**
  ```bash
  curl --location --request GET 'localhost:8082/produtos/produtos/dados-entrega/4'
  ```

# Gerenciamento de Usuários
## **POSTs**

### **Registrar Usuário**
- **URL:** `POST localhost:8082/usuarios/auth/register`
- **Descrição:** Registra novo usuário.
- **Corpo da Requisição:**
  ```json
  {
    "login":"teste",
    "password":"123456",
    "roles":["ADMIN","USER"]
  }
  ```
- **Exemplo:**
  ```bash
   curl --location --request POST 'localhost:8082/usuarios/criar-usuario' \
   --header 'Content-Type: application/json' \
   --data-raw '{
     "login": "teste",
     "password": "123456",
     "roles": [
       "ADMIN",
       "USER"
    ]
  }'

  ```

### **Login de Usuário**
- **URL:** `POST localhost:8082/entregas/entregas/criar-entrega`
- **Descrição:** Cria uma nova entrega.
- **Corpo da Requisição:**
  ```json
  {
    "login":"teste",
    "password":"123456"
  }
  ```
- **Exemplo:**
  ```bash
    curl --location --request POST 'localhost:8082/usuarios/criar-usuario' \
   --header 'Content-Type: application/json' \
   --data-raw '{
     "login": "teste",
     "password": "123456"
   }'

  ```


# Deploy

#### Deploy local(Docker Compose):

Basta acessar https://github.com/matheushajer/projeto-fiap-fase-5/tree/master/deployment/images para encontrar os Dockerfiles das imagens criadas.

Cada Dockerfile é utilizado para um microserviço diferente e para o banco de dados Postgresql.

Para facilitar a execução basta ir até https://github.com/matheushajer/projeto-fiap-fase-5/blob/master/deployment/docker-compose.yml baixar para sua máquina e utilizar o comando abaixo:

``` 
docker-compose up 

```

OBS: Mais informações em: https://docs.docker.com/compose/

#### Links:
- Eureka: http:localhost:8081
- Gateway: http:localhost:8082
- Repositório de imagens: https://hub.docker.com/repository/docker/yuriesena/fase5/tags

## Autores

- Cleyton Sales
- Déborah Souza
- Karoline Leite
- Matheus Hajer
- Yuri Sena
