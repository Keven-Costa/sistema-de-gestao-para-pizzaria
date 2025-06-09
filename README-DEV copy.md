# Ambiente de Desenvolvimento - Gestão de Pizzaria 🛠️ (Em desenvolvimento ⚠️)

Este guia explica como configurar o projeto localmente para desenvolvimento e contribuição. (este guia não esta pronto ⚠️)

## 📋 Pré-requisitos

| Ferramenta         | Versão    | Como verificar          | Observações              |
|--------------------|-----------|-------------------------|--------------------------|
| Java               | 11+       | `java -version`         | JDK instalado            |
| Maven              | 3.6+      | `mvn -v`                | Para gerenciamento de dependências |
| MySQL              | 8.0+      | `mysql --version`       | Servidor rodando localmente |
| Git                | 2.20+     | `git --version`         |                          |
| IDE                | -         | -                       | Eclipse, IntelliJ ou VS Code com extensão Java |

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
Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:
``` 
DB_USERNAME=[nome_usuario_aplicacao]
DB_PASSWORD=[senha_usuario_aplicacao]
DB_ROOT_PASSWORD=[senha_root_mysql]
```


## 3. Configure o banco de dados (MySQL):

- Crie um banco MySQL chamado **gestao_pizzaria**
- Configure as credenciais com  a mesmas do arquivo **.env**

...

Via IDE ou comando: mvn spring-boot:run


### 4. Execute esse comando

```bash
export $(grep -v '^#' .env | xargs) && mvn spring-boot:run
```

### 5. Acessando a aplicação
Após a execução do comando acima, a aplicação estará rodando em http://localhost:8080.

## Rodar a aplicação com o Docker

[acesse aqui](#)

## 📊 Diagramas
→ [Ver diagramas do projeto](#)