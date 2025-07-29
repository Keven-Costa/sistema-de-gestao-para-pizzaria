


# Sistema de Gestão para Pizzaria 🍕

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow) 
![Tecnologias](https://img.shields.io/badge/tech-Java%20|%20Spring%20|%20MySQL%20|%20Thymeleaf-blue) 
![License](https://img.shields.io/badge/license-MIT-green)


Projeto pessoal desenvolvido para fins de aprendizagem, implementando um sistema  para pizzaria com:


- **Área Pública**: Para clientes montarem e acompanharem pedidos
- **Área Administrativa**: Com autenticação para gestão do negócio
- **Stack**: Java, Spring Boot, MySQL, Thymeleaf e Docker


*Observação: Projeto desenvolvido para fins de aprendizagem, algumas funcionalidades podem ser limitadas.*

## 📌 Índice

- [Imagens do Projeto](#tecnologias-utilizadas)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades Implementadas](#-funcionalidades-implementadas)
- [Estrutura de Rotas](#estrutura-de-rotas)
- [Acesso Administrativo](#-acesso-administrativo)
- [Pré-requisitos](#pre-requisitos)
- [Execução](#execucao)
- [Diagramas](#-diagramas)
- [Licença](#-licença)


## 📷 Imagens do Projeto <a id="imagens-do-projeto"></a>

![Imagens do projeto](telas-projetos.gif)



## <a id="tecnologias-utilizadas"></a> 🛠️ Tecnologias Utilizadas


| Camada         | Tecnologias                              |
|----------------|------------------------------------------|
| Backend        | Java 11, Spring Boot 2.7, Spring Security|
| Banco de Dados | MySQL 8.0                                |
| Frontend       | Thymeleaf, Bootstrap 5                   |
| Infra          | Docker, Docker Compose                   |

## 🎯 Funcionalidades Implementadas

### Área do Cliente
- ✔️ Montagem interativa de pedidos
- ✔️ Checkout simplificado
- ✔️ Rastreamento do pedido

### Área Administrativa
- ✔️ Dashboard 
- ✔️ Gestão de pedidos
- ✔️ Gestão de estoque de ingredientes
- ✔️ Gerenciamento de cardápio
- ✔️ Autenticação segura (Spring Security)

## <a id="estrutura-de-rotas"></a> 🗺️ Estrutura de Rotas

### Cliente (Acesso Livre)
| Rota                          | Descrição                     |
|-------------------------------|-------------------------------|
| `/`                           | Página inicial                |
| `/montagem-pedidos?id={id}`   | Personalização do pedido      |
| `/checkout`                   | Finalização da compra         |
| `/confirmacao-pedido/{id}`    | Confirmação do pedido         |
| `/rastreamento-pedido/{id}`   | Acompanhamento do pedido      |

### Administração (Protegido)
| Rota                          | Descrição                     |
|-------------------------------|-------------------------------|
| `/admin/login`                | Página de autenticação        |
| `/admin/dashboard`            | Visão geral da operação       |
| `/admin/gestao-pedidos`       | Gerenciamento de pedidos      |
| `/admin/gestao-ingredientes`  | Controle de estoque           |
| `/admin/painel-promocoes`     | Gestão de promoções           |
| `/admin/gerenciar-pizzas`     | Administração do cardápio     |

## 🔐 Acesso Administrativo

Para acessar a área administrativa:

1. Acesse: http://localhost:8080/admin/login
2. Credenciais padrão:

- Usuário: ```admin```  
- Senha: ```123456```



## <a id="pre-requisitos"></a> ⚙️ Pré-requisitos

| Componente       | Versão mínima | Como verificar       |
|------------------|---------------|----------------------|
| Docker           | 20.10+        | `docker --version`   |
| Docker Compose   | 1.29+         | `docker-compose -v`  |



##   <a id="execucao"></a> ▶️ Execução

*Rodando a aplicação com Docker*

### 1. Clone o repositório

Primeiro, clone este repositório para a sua máquina:

```bash
git clone https://github.com/[seu-usuario]/gestao-pizzaria.git

```
Navegue até a raiz do projeto
```bash
cd gestao-pizzaria
```

### 2. Crie um arquivo .env
Crie um arquivo `.env` na raiz do projeto

#### No Windows (CMD ou PowerShell):

No CMD:

```bash
type nul > .env
```

No PowerShell:

```bash
New-Item -Path .env -ItemType File
```


#### No Linux:

```bash
touch .env
```

Adicione as seguintes variáveis de ambiente com suas credenciais:

``` 
DB_USERNAME=usuario_projeto
DB_PASSWORD=senha_projeto
DB_ROOT_PASSWORD=senha_root_mysql
```

- Você pode substituir os valores **usuario_projeto**, **senha_projeto**, **senha_root_mysql** pelas suas credenciais reais.

### 3. Verifique se o doker esta rodando
Caso não esteja iniciado:

- Linux: ```sudo systemctl start docker```

- Windows/Mac: Inicie o Docker Desktop

### 4. Construa e execute os containers

```bash
docker compose up --build
```

### 5. Acessando a aplicação
Após a execução do comando acima, a aplicação estará rodando em http://localhost:8080.

## Rodar a aplicação como desenvolvedor

[Acesse aqui](#https://github.com/Keven-Costa/sistema-de-gestao-para-pizzaria/blob/main/README-DEV.md)

## 📊 Diagramas
→ [Ver diagramas do projeto](#)

