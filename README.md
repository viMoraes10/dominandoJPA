 ### Introdução
Você já se perguntou qual é a melhor forma de buscar informações em um banco de dados usando Java Persistence API (JPA)? Neste projeto, exploraremos técnicas que podem significativamente melhorar o desempenho do seu software. Uma simples busca no banco de dados, quando executada corretamente, pode aumentar a velocidade do seu software em mais de 100%.

#### O que é Java Persistence API (JPA)?
A Java Persistence API (JPA) é um elemento fundamental do ecossistema Java, desempenhando um papel crucial na interação entre aplicações Java e bancos de dados. Essa especificação de mapeamento objeto-relacional permite que desenvolvedores manipulem dados em bancos de dados por meio de operações orientadas a objetos, sem a necessidade de lidar diretamente com operações SQL. Com JPA, você pode abstrair a complexidade das operações de banco de dados e concentrar-se mais na lógica de negócios de suas aplicações.

### Configuração do Ambiente

Para iniciar qualquer projeto Spring Boot, eu geralmente utilizo o [Spring Initializr](https://start.spring.io/), uma interface web que simplifica a criação de projetos Java com Spring Boot. Este recurso é extremamente útil para configurar rapidamente a estrutura básica do projeto com as dependências necessárias.

#### Dependências Utilizadas
- **Spring Data JPA**: Facilita as interações com o banco de dados, permitindo o uso de JPA para mapear objetos Java a tabelas em um banco de dados relacional.
- **PostgreSQL Driver**: Conecta a aplicação Spring Boot ao PostgreSQL, otimizando operações de banco de dados.
- **Spring Web**: Permite a construção de aplicações web e APIs, facilitando testes e interações durante o desenvolvimento.

### Arquitetura do Projeto: Modelo MVC
Este projeto segue o modelo arquitetural Model-View-Controller (MVC), que separa responsabilidades dentro da aplicação, facilitando a manutenção e escalabilidade. Aqui está como cada parte é representada:

- **Model**: Camada de dados e lógica de negócios.
- **View**: Responsável pela apresentação dos dados (não aplicável diretamente em APIs REST).
- **Controller**: Gerencia interações entre o Model e o View, processando requisições dos usuários.

#### Configuração da Entidade
As entidades são configuradas com anotações do JPA para mapear as tabelas de banco de dados. Exemplo de uma entidade:

```java
@Entity
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fundacao")
    private Date fundacao;
    @Column(name = "estadio")
    private String estadio;
}
```

#### Configuração do RestController
O `RestController` está configurado com endpoints para inserção e consulta de dados, facilitando a avaliação de diferentes métodos de busca:

```java
@RestController
@RequestMapping("/clubes")
public class ClubeController {
     @PostMapping(value = "/add-clubes")
    public ResponseEntity<?> addClube(@RequestBody List<ClubeDTO> clubeDTO){
        try{
            return ResponseEntity.ok(clubeService.addClubes(clubeDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    @GetMapping(value = "/estado/{estado}")
    public ResponseEntity<?> getByEstado(@PathVariable String estado){
        try{
            return ResponseEntity.ok(clubeService.getByEstado(estado));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }
} 
```

### Teste de Desempenho
Realizei a postagem de 77 clubes de futebol e implementei várias técnicas de busca no método `getByEstado`. Os testes revelaram que o método JPQL é o mais eficiente, enquanto o Query Methods teve o pior desempenho.

### Conclusão
Explore mais sobre este projeto visitando o [meu repositório do GitHub](https://github.com/viMoraes10) e [meu LinkedIn](https://www.linkedin.com/in/v1moraes/). 

 
