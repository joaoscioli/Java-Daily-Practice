# Java Daily Practice

Bem-vindo ao **Java-Daily-Practice**, um repositório avançado para prática diária em Java, voltado para desenvolvedores com experiência sênior. Este repositório foca em projetos complexos e requisitos comuns em entrevistas técnicas de alto nível, utilizando as versões mais recentes da linguagem (Java 23, lançada em setembro de 2024) e frameworks populares como Spring Boot 3.3, Hibernate 6.5 e JUnit 5.11. Cada semana é organizada em um pacote específico, com projetos que exploram tópicos avançados e boas práticas.

## Objetivo
- Dominar recursos avançados de Java 23 (ex.: pattern matching, virtual threads, JEP 482).
- Construir projetos robustos com Spring Boot, Hibernate e outras ferramentas modernas.
- Resolver desafios de entrevistas técnicas para posições sênior.
- Criar um portfólio de projetos sofisticados e escaláveis.

## Pré-requisitos
- JDK 23 instalado.
- IDE (IntelliJ IDEA ou VS Code com extensões Java).
- Maven 3.9.9 para gerenciamento de dependências.
- Experiência sênior em Java (conhecimento em concorrência, design patterns, microserviços).
- Docker para bancos de dados e testes de integração.
- Git e GitHub para controle de versão.

## Estrutura do Repositório
Os projetos são organizados por semana em pacotes Maven separados, cada um com sua própria estrutura de código e dependências.

```
Java-Daily-Practice/
├── semana-01/
│   ├── dia-01/
│   ├── dia-02/
│   ├── ...
│   ├── pom.xml
│   └── README.md
├── semana-02/
│   ├── dia-08/
│   ├── dia-09/
│   ├── ...
│   ├── pom.xml
│   └── README.md
...
├── semana-04/
│   ├── dia-22/
│   ├── ...
│   ├── pom.xml
│   └── README.md
└── README.md
```

## Plano de 30 Dias
O plano é dividido em 4 semanas, cada uma com 7 ou 8 projetos, agrupados por temas avançados e organizados em pacotes semanais. Cada projeto utiliza Java 23 e frameworks atualizados, com foco em cenários reais de nível sênior.

### Semana 1: Concorrência e Java 23 (Pacote: `semana-01`)
Foco em virtual threads (JEP 444), structured concurrency (JEP 453) e outros recursos de Java 23.
- **Dia 1**: Criar um servidor HTTP com virtual threads para processar 10.000 requisições simultâneas, usando `java.net.http`.
- **Dia 2**: Implementar um pipeline de processamento de dados com `StructuredTaskScope` para agregação paralela de resultados.
- **Dia 3**: Desenvolver um sistema de retry assíncrono com `CompletableFuture` e circuit breaker para chamadas externas.
- **Dia 4**: Criar um programa que usa `ScopedValue` (JEP 464) para propagação de contexto em threads virtuais.
- **Dia 5**: Implementar um crawler web paralelo com virtual threads e `HttpClient` para extrair dados de 100 URLs.
- **Dia 6**: Escrever testes de concorrência com JUnit 5 e `VirtualThread` para simular carga em um serviço.
- **Dia 7**: Otimizar um sistema de filas com `ExecutorService` e virtual threads, comparando desempenho com threads tradicionais.

### Semana 2: Spring Boot Avançado (Pacote: `semana-02`)
Foco em Spring Boot 3.3, REST APIs, segurança e observabilidade.
- **Dia 8**: Criar uma API REST com Spring Boot 3.3 para gerenciamento de usuários, usando record patterns (JEP 440).
- **Dia 9**: Adicionar autenticação JWT com Spring Security 6.3, incluindo refresh tokens e RBAC.
- **Dia 10**: Implementar um sistema de eventos assíncronos com Spring `@Async` e virtual threads.
- **Dia 11**: Integrar Spring Boot com OpenTelemetry para monitoramento de métricas e tracing distribuído.
- **Dia 12**: Criar endpoints reativos com Spring WebFlux e R2DBC para um serviço de notificações.
- **Dia 13**: Implementar um sistema de cache distribuído com Spring Cache e Redis.
- **Dia 14**: Escrever testes de integração com Testcontainers para validar endpoints com PostgreSQL.

### Semana 3: Microserviços e Escalabilidade (Pacote: `semana-03`)
Foco em arquitetura de microserviços com Spring Cloud e boas práticas.
- **Dia 15**: Criar um microserviço de pedidos com Spring Cloud Gateway para roteamento dinâmico.
- **Dia 16**: Implementar comunicação entre microserviços com Spring Cloud Stream e Kafka.
- **Dia 17**: Configurar resiliência com Resilience4j (circuit breaker, retry, rate limiter) em um microserviço.
- **Dia 18**: Criar um serviço de descoberta com Spring Cloud Netflix Eureka.
- **Dia 19**: Implementar um sistema de balanceamento de carga com Spring Cloud LoadBalancer.
- **Dia 20**: Desenvolver um microserviço com CQRS usando Spring Data JPA e Axon Framework.
- **Dia 21**: Configurar CI/CD com GitHub Actions para deploy de um microserviço em Docker.

### Semana 4: Projetos de Alto Nível e Integração (Pacote: `semana-04`)
Foco em projetos completos, integração de sistemas e otimização.
- **Dia 22**: Criar um sistema de e-commerce com Spring Boot, JPA e Keycloak para autenticação SSO.
- **Dia 23**: Implementar um serviço de busca full-text com Spring Data Elasticsearch.
- **Dia 24**: Desenvolver um pipeline ETL com Spring Batch para processar grandes arquivos CSV.
- **Dia 25**: Criar um sistema de recomendação com Spring Boot e Apache Mahout.
- **Dia 26**: Integrar uma API com GraphQL usando Spring for GraphQL e schema-first design.
- **Dia 27**: Otimizar uma API REST com Spring AOT (Ahead-of-Time Compilation) para redução de latência.
- **Dia 28**: Criar um sistema de notificações em tempo real com Spring WebSocket e STOMP.
- **Dia 29**: Implementar um serviço de relatórios com JasperReports e Spring Boot.
- **Dia 30**: Desenvolver um projeto final: plataforma de agendamento com Spring Boot, Kubernetes e monitoramento com Prometheus/Grafana.

## Como Usar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/Java-Daily-Practice.git
   ```
2. Navegue até a pasta da semana desejada (ex.: `semana-01`) e, depois, até o dia específico (ex.: `dia-01`).
3. Instale as dependências:
   ```bash
   mvn clean install
   ```
4. Siga as instruções no `README.md` de cada dia para executar o projeto.
5. Use Docker para subir bancos de dados ou serviços externos, quando necessário:
   ```bash
   docker-compose up
   ```
6. Envie suas soluções com commits claros, seguindo o padrão Conventional Commits (ex.: `feat(dia-01): implementa servidor HTTP com virtual threads`).

## Dependências Principais
- **Java**: 23
- **Spring Boot**: 3.3
- **Spring Security**: 6.3
- **Hibernate**: 6.5
- **JUnit**: 5.11
- **Testcontainers**: 1.20
- **Kafka**: 3.8
- **Redis**: 7.4
- **PostgreSQL**: 16
- **Docker**: Latest

## Recursos Adicionais
- [Documentação do Java 23](https://openjdk.org/projects/jdk/23/)
- [Spring Boot 3.3](https://spring.io/projects/spring-boot)
- [Hibernate 6.5](https://hibernate.org/orm/releases/6.5/)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Resilience4j](https://resilience4j.readme.io/)
- [OpenTelemetry](https://opentelemetry.io/)
- [LeetCode](https://leetcode.com/) para desafios avançados.

## Contribuições
Contribuições são bem-vindas! Siga os passos:
1. Faça um fork do repositório.
2. Crie uma branch (`git checkout -b feat/novo-projeto`).
3. Commit suas alterações (`git commit -m "feat: adiciona novo projeto para dia XX"`).
4. Envie para o repositório remoto (`git push origin feat/novo-projeto`).
5. Abra um Pull Request.

## Licença
Licenciado sob a [MIT License](LICENSE).

---

**Desafie-se e eleve suas habilidades!** 🚀