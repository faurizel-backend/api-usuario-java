# API Usuario - Spring Boot

Uma API RESTful para gerenciamento de usuários desenvolvida em Java com Spring Boot, JPA e MySQL.

## 📋 Visão Geral

Este projeto implementa uma API simples mas funcional para cadastro e autenticação de usuários. É um projeto educacional que demonstra as melhores práticas de desenvolvimento com Spring Boot, incluindo:

- Arquitetura em camadas (Controller → Service → Repository → Entity)
- Persistência de dados com JPA/Hibernate
- Validação de dados
- Endpoints RESTful
- CORS habilitado
- Logging estruturado com Lombok

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| Java | 17 | Linguagem de programação |
| Spring Boot | 3.5.5 | Framework web |
| Spring Data JPA | 3.5.5 | ORM para persistência |
| MySQL | Latest | Banco de dados relacional |
| Lombok | 1.18.40 | Gerador de código boilerplate |
| Maven | 3.9+ | Gerenciador de dependências |

## 📂 Estrutura do Projeto

```
api-usuario-java/
├── src/
│   ├── main/
│   │   ├── java/br/gov/sp/etec/api_usuario/
│   │   │   ├── ApiUsuarioApplication.java      # Classe principal
│   │   │   ├── controller/
│   │   │   │   └── UsuarioController.java      # Endpoints REST
│   │   │   ├── service/
│   │   │   │   └── UsuarioService.java         # Lógica de negócio
│   │   │   ├── entity/
│   │   │   │   └── UsuarioEntity.java          # Modelo de dados
│   │   │   └── repository/
│   │   │       └── UsuarioRepository.java      # Acesso aos dados
│   │   └── resources/
│   │       └── application.properties          # Configurações
│   └── test/
│       └── java/.../ApiUsuarioApplicationTests.java
├── pom.xml                                     # Configuração Maven
└── mvnw/mvnw.cmd                               # Maven Wrapper

```

## 🚀 Como Executar

### Pré-requisitos

- JDK 17 ou superior instalado
- MySQL 8.0 ou superior em execução
- Maven 3.9+ (opcional, incluso Maven Wrapper)

### Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/faurizel-backend/api-usuario-java.git
   cd api-usuario-java
   ```

2. **Configure o banco de dados**
   - Certifique-se que MySQL está rodando
   - Crie um banco de dados (opcional, Hibernate criará automaticamente)
   ```sql
   CREATE DATABASE user_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Configure as credenciais** (se necessário)
   - Edite `src/main/resources/application.properties`
   - Altere `spring.datasource.username` e `spring.datasource.password` conforme necessário

4. **Execute a aplicação**
   
   Usando Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   
   Ou com Maven instalado:
   ```bash
   mvn spring-boot:run
   ```

5. **Verifique se está rodando**
   - A aplicação estará disponível em: `http://localhost:8080`

## 📡 API Endpoints

### 1. Registrar Novo Usuário

**POST** `/usuario`

**Request Body:**
```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha123"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha123"
}
```

**Validações:**
- Nome não pode ser vazio
- Email não pode ser vazio
- Email deve ser único no banco de dados

---

### 2. Autenticar Usuário

**GET** `/usuario/autenticar`

**Query Parameters:**
- `email` (required): Email do usuário
- `senha` (required): Senha do usuário

**Example:**
```
GET /usuario/autenticar?email=joao@example.com&senha=senha123
```

**Response:**
```
true  (se autenticado com sucesso)
false (se email/senha incorretos)
```

## 🏗️ Arquitetura

A aplicação segue o padrão de arquitetura em camadas:

```
┌─────────────────────────────────────────┐
│         CLIENT (Frontend/Postman)       │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│      CONTROLLER (UsuarioController)     │
│   - Recebe requisições HTTP             │
│   - Valida entrada                      │
│   - Retorna JSON                        │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│       SERVICE (UsuarioService)          │
│   - Lógica de negócio                   │
│   - Validações                          │
│   - Processamento de dados              │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│    REPOSITORY (UsuarioRepository)       │
│   - Acesso aos dados                    │
│   - Consultas ao banco                  │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│      DATABASE (MySQL - tb_usuario)      │
│   - Persistência dos dados              │
└─────────────────────────────────────────┘
```

## 📊 Modelo de Dados

### Tabela: `tb_usuario`

| Campo | Tipo | Restrições | Descrição |
|-------|------|-----------|-----------|
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Identificador único |
| `nome` | VARCHAR(255) | NOT NULL | Nome completo do usuário |
| `email` | VARCHAR(255) | NOT NULL, UNIQUE | Email único do usuário |
| `senha` | VARCHAR(255) | - | Senha do usuário |

## 🔍 Exemplo de Uso

### Usando cURL

**Registrar um usuário:**
```bash
curl -X POST http://localhost:8080/usuario \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Silva",
    "email": "maria@example.com",
    "senha": "senha456"
  }'
```

**Autenticar um usuário:**
```bash
curl "http://localhost:8080/usuario/autenticar?email=maria@example.com&senha=senha456"
```

### Usando Postman

1. Importe as requisições no Postman
2. Configure as variáveis de ambiente conforme necessário
3. Execute as requisições

## 🧪 Testes

Para executar os testes:

```bash
./mvnw test
```

Os testes estão localizados em:
```
src/test/java/br/gov/sp/etec/api_usuario/
```

## 🐛 Troubleshooting

| Problema | Solução |
|----------|---------|
| Erro de conexão MySQL | Verifique se MySQL está rodando e as credenciais estão corretas em `application.properties` |
| Porta 8080 já em uso | Altere a porta em `application.properties` com `server.port=8081` |
| Email duplicado | Cada email deve ser único, tente com um email diferente |
| Erro ao iniciar aplicação | Limpe o cache Maven: `./mvnw clean install` |

## 📝 Logging

A aplicação usa SLF4J com Lombok para logging:

- **DEBUG**: Informações detalhadas de desenvolvimento
- **INFO**: Informações gerais de operação
- **ERROR**: Erros durante execução

Exemplo de log:
```
[DEBUG] Cadatrando... UsuarioEntity(id=null, nome=João, email=joao@example.com, senha=123)
[INFO] Usuario logando com joao@example.com
[INFO] Autenticado com joao@example.com sucesso
```

## 🔒 Segurança

⚠️ **Importante**: Este é um projeto educacional. Para produção:

- ✅ Implementar hash de senhas (BCrypt, Argon2)
- ✅ Adicionar validação de email
- ✅ Implementar JWT para autenticação
- ✅ Adicionar HTTPS
- ✅ Validar entrada com annotations do Bean Validation
- ✅ Implementar rate limiting
- ✅ Adicionar logs de auditoria

## 📚 Recursos Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok Documentation](https://projectlombok.org/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [RESTful API Best Practices](https://restfulapi.net/)

## 👥 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é licenciado sob a licença MIT - veja o arquivo LICENSE para detalhes.

## 👨‍💼 Autor

**Desenvolvido para:** ETEC - Escola Técnica Estadual de São Paulo

**Organização:** faurizel-backend

**Data de Criação:** Outubro de 2025

---

**Última Atualização:** Outubro de 2025
