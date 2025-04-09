
# Sistema de Gestão para Pizzaria 🍕

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow) 
![Docker](https://img.shields.io/badge/Docker-required-blue) 
![License](https://img.shields.io/badge/license-MIT-green)

Sistema completo para gestão operacional de pizzarias com controle de pedidos, estoque, clientes e relatórios integrados.

## 📌 Índice

- [Funcionalidades](#-funcionalidades)
- [Pré-requisitos](#%EF%B8%8F-pré-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Uso](#-uso)
- [Desenvolvimento](#-desenvolvimento)
- [Licença](#-licença)

## 🚀 Funcionalidades

- **Pedidos**
  - Registro de pedidos 
  - Acompanhamento em tempo real
  - Histórico completo
- **Estoque**
  - Controle de ingredientes
  - Alertas de reposição
  - Custo por produto
- **Clientes**
  - Cadastro com histórico
  - Fidelidade
  - Endereços
- **Relatórios**
  - Vendas por período
  - Produtos mais vendidos
  - Desempenho de entregadores

## ⚙️ Pré-requisitos

| Componente       | Versão mínima | Como verificar       |
|------------------|---------------|----------------------|
| Docker           | 20.10+        | `docker --version`   |
| Docker Compose   | 1.29+         | `docker-compose -v`  |
| RAM disponível   | 4GB           | -                    |

## 🛠 Instalação

### Método 1: Docker Simples

#### Construir a imagem:
```bash

docker build -t pizzaria-app .
```

#### Executar container:
```bash

docker run -p 8081:8081 pizzaria-app

```