# JPA

## O que é ORM?

> ORM = Object Relational Mapping = Mapeamento Objeto Relacional

ORM não algum tipo de ferramenta ou tecnologia mas uma técnica ou conceito para manipulação de dados em bases relacionais. Surgiu na esteira da popularização do paradigma POO. O objetivo foi facilitar e flexibilizar a forma como os dados são manipulados em bases relacionais.  

```mermaid
  flowchart LR
    ModelA[Model A] --> ORM[ORM]
    ModelB[Model B] --> ORM
    ORM --> DB[DB]
```

Toda a camada de dados relacionais, Conexões e Querys que o desenvolvedor teria que implentar é abstraido por algum framework ORM. O foco passa a ser na declaração dos modelos de dados, ou seja, as classes e seus atributos que correspondem às tabelas e suas colunas em uma base relacional. Apesar disso, é possível declarar explicitamente querys com os frameworks. Cria uma ponte entre linuagen POO e bases de dados relacionais. Faz isso estabelendo associações:

- Tabela <-> Classe
- Colunas <-> Atributos

Além disso, faz parte de um ORM operações básicas do tipo **C**reate, **R**ead, **U**pdate e **D**elete.

## Frameworks ORM para Java

- Hibernate
- ActiveJPA
- Cayenne
- Ebean

## Qual é a vantagem de utilizar um ORM?

- **Produtividade**:
- **Padronização**: os frameworks ORM
- **Segurança**: os frameworks de ORM adicionam proteções extras, como a prevenção de SQL Injection.
- **Portabilidade**: o código escrito com o auxílio de um ORM para uma base de dados pode ser facilmente migrada para outra.

## Quais são as desvantagens ao utilizar um ORM?

- **Desempenho**: Para bases e modelos complexos, o desempenho pode ser prejudicado.
- **Curva de Aprendizado**: Aprender como operar um framework ORM é mais dispensioso que aprender a operar com SQL.
- **Dependência da Abstração**: Pode gerar uma certa dependência do framework

---

## O que é JPA?

JPA, sigla para Java Persistence API, é uma especificação que orienta como os frameworks de persistência Java devem ser implementados. Surgiu como uma necessidade de padronizar os diferentes tipos de ORM que estavam surgindo na época. A JPA foi impulsionada pelo Hibernate. EplipseLink é um framework de referência, ou seja, é um dos primeiros a implentar alguma especificação nova da API Java Persistence

JPA é como uma classe abstrada ou uma interface que necessita que seus métodos sejam implementados pelo utilizador.

O JPA é também especifica anotações para fazer alguns mapeamentos, como:

- **@Entity**: Indica que os objetos da classe dessa anotações podem ser persistidos.
- **@Table**: Faz o mapeamento da classe com a tabela pelo nome dessa última.
- **@Column**: Faz o mapeamento entre um atributo e uma coluna.
- **@Id**: Indica que o atributo da classe é a chave primária da tabela.

O JPA necessita de um arquivo de configuração especificando qual o banco, o usuário, a senha e outras propriedades. Este arquivo é o **persistence.xml** e pode ser parecido com o exemplo abaixo:

```xml
  <!-- persistence.xml -->
  <persistence-unit name="Banco_A" transaction-type="JTA">
    <provider>org.hibernate.ejb.HibernatePersistence</provider>

    <class>com.jpa.model.ClasseA</class>

    <properties>
      <property name="javax.persistence.jdbc.driver" value="org.hsqldb.jdbcDriver"/>
      <property name="javax.persistence.jdbc.url" value="jdbc:hsqldb:mem:hibernate"/>
      <property name="javax.persistence.jdbc.user" value="sa"/>
      <property name="javax.persistence.jdbc.password" value="senha"/>
    </persistence>
  </persistence-unit>
```

Um detalhe importante dessa configuração é a propriedade `transaction-type` da `persistence-unit`. O valor `JTA` indica que o gerenciamento das transações será delegada para um servidor. Portanto, é necessário que a utilização de um servidor como Jboss ou Glassfish. O valor `RESOURCE_LOCAL` indica que o gerenciamento das transações serão realizadas localmente pelo usuário.

É possível controlar ter um controle manual das transações, como:

```java
  public class Jpa {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banco_A");

    public static void main(String[] args) throws Exception {
      EntityManager entityManager = emf.createEntityManager();
      try {
        entityManager.getTransaction().begin();
        // ...
        entityManager.getTransaction().commit();
      } catch (Exception e) {
        if (entityManager.isOpen())
          entityManager.getTransaction().rollback();
      } finally {
        if (entityManager.isOpen())
          entityManager.close();
      }
    }
  }
```

A outra forma de controlar é delegando sua atribuição a um servidor, como Jboss, Glassfish. Esses servidores fazem uso da **Injeção de Dependência** para e de um EJB, como no exemplo abaixo:

```Java
  @Stateless
  public class ClasseJPA() {
    @PersistenceContext
    private EntityManager entityManager;
  }
```

Há também a possibilidade de utilizar um framework como o Spring e Hibernate.

## Ciclo de Vida das Entidades JPA

```mermaid
  stateDiagram-v2
    [*] --> Transient : new
    Transient --> Managed : persist()
    Managed --> Detached : close() / clear()
    Detached --> Managed : merge()
    Managed --> Removed : remove()
    Managed --> BD : commit() / flush()
    BD --> Managed : find() / createQuery()
    BD --> [*]
```

- **Transient**: O objeto foi instânciado, porém não foi reportado para o EntityManager. Assim, esse último desconhece essa entidade.
- **Managed**: Quando a entidade foi passada para o EntityManager. Ele irá conseguir persistir e gerenciar a entidade na base de dados.
- **Detached**: A entidade é conhecida pelo EntityManager pois já foi genrenciada por ele, entretanto, não está sendo mais. Qualquer alteração realizado no objeto da entidade não será refletida no banco.

> Para fazer o `merge()` é necessário que as entidades tenham explicitamente um construtor `default`, como:
>
> ```java
> public Produto(){}
> ```
>
> Além disso, este método não atualiza para `Managed` a instância passada em ``em.merge(produto)``. Ele irá devolver uma referência para um novo objeto que está sendo gerenciado. Logo, o código correto é ``produto = em.merge(produto)``.

## Relacionamentos

Por padrão, a JPA não assume qualquer tipo de cardinalidade entre as entidades. Portanto, é obrigatório especificar qual o tipo de relacionamento as tabelas irão assumir. Para isso, é possível utilizar as tags:

- `@JoinTable`: Capaz de configurar um relacionamento entre entidades especificando qual a tabela, as colunas e etc.
- `@ManyToOne`: Cria um relacionamento **Muitos para Um** entre as entidades.
- `@ManyToMany`: Cria um relacionamento **Muitos para Muitos** entre as entidades.
- `@OneToMany`: Cria um relacionamento **Um para Muitos** entre as entidades.
  - Existe um parâmetro chamado `cascade` que serve para propagar as operações realizadas em uma entidade para seus relacionamentos.
- `@OneToOne`: Cria um relacionemnto **Um para Um** entre as entidades.

## JPQL

É um mecanismo de busca similar ao SQL para JPA. A intenção é simplificar consultas às bases de dados. Uma instrução em SQL para recuperar todos os elementos da tabela `produto` seria:

> ``SELECT * FROM produto``

Enquanto que para JPQL a instrução fica:

> ``SELECT p FROM Produto p``

Onde `Produto` é o nome da classe mapeada como uma entidade do banco de dados.

É possível realizar consultas com filtros - similiar ao WHERE do SQL - e com Joins. Suponha que uma tabela `produto` (mapeada na classe `Produto`) tenha um relacionamento com a tabela `categoria` (mapeada na classe `Categoria`). A consulta ficaria:

> ``SELECT p FROM Produto p WHERE p.Categoria.nome = :nome``

Onde `:nome` é um argumento nomeado que será substituído posteriormente por um valor. Caso seja necessário especificar qual coluna deve ser retornada, pode-se fazer:

> ``SELECT p.nome FROM Produto p WHERE p.Categoria.nome = :nome``

### named query

---

## Carregamentos Lazy e Eager

Supondo que em um banco de dados exista o relacionamento `AUTOR (1) <--> (0..n) LIVRO` e as tabelas já tenham sido mapeadas, quando a consulta abaixo for realizada:

```java
  EntityManager em = JPAUtil.getEntityManager();
  Livro livro = em.find(Livro.class, 1L);
  System.out.println(livro.getTitulo());
```

O framework de JPA - por padrão - irá realizar a consulta fazendo um join com a tabela `LIVRO`, acarretando em um overhead quando esse relacionamento não é necessário. Isso acontece com os relacionamento `@ManyToOne`, `@OneToOne`. Ou seja, relacionamento `*ToOne`. Já relacionamentos `*ToMany` não possuem essa característica.  

O padrão dos relacionamentos `*ToOne` é `Eager`, ou seja, carregar as informações de imediato. Já para os relacionamentos `*ToMany`, o padrão é `Lazy`, carregando as informações apenas quando forem requisitadas explicitamente no código.

É possível alterar esse comportamento com o atributo `fetch`. Assim, o exemplo acima ficaria:

```java
  @Entity
  @Table(name = "LIVRO")
  public class Livro {

    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;
  }
```

Uma recomendação seria alterar todos os relacionamentos `*ToOne` para `LAZY`. Entretanto, um efeito colateral é o seguinte:

```java
  EntityManager em = JPAUtil.getEntityManager();
  Livro livro = em.find(Livro.class, 1L);
  em.close();
  System.out.println(livro.getAutor().getNome());
```

O trecho de código acima gera o erro `LazyInitializationException`, pois o EntityManager foi fechado e como as informações do autor do livro não haviam sido carregadas, não foi possível executar a query para o autor em `livro.getAutor()`.

Uma solução é a criação de uma query específica que retorne o autor. A consulta ficaria `"SELECT l FROM Livro l JOIN FETCH l.autor WHERE l.id = :id"`, onde o `JOIN FETCH` indica uma junção de tabelas mas também o carregamento dos dados filtrados. Nesse caso, a chave será o identificados do autor.

## Consultas Dinâmicas e Criteria API

Por vezes, é desejado que as consultas tenham uma certa flexibilidade na lista de parâmetros onde alguns podem se tornar opcionais. Tradicionalmente, é preciso verificar quais parâmetros do método estão sendo enviados e quais não estão para montar dinamicamente a consulta, como no exemplo abaixo:

```java
  public List<Product> searchByParams(String name, BigDecimal price, LocalDate creationDate) {
    String jpql = "SELECT p FROM Product p WHERE 1=1 ";

    if (name != null && !name.trim().isEmpty()) {
      jpql += " AND p.name = :name ";
    }

    if (price != null) {
      jpql += " AND p.price = :price ";
    }

    if (creationDate != null) {
      jpql += " AND p.creation_date = :creationDate ";
    }

    TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);

    if (name != null && !name.trim().isEmpty()) {
      query.setParameter("name", name);
    }

    if (price != null) {
      query.setParameter("price", price);
    }

    if (creationDate != null) {
      query.setParameter("creationDate", creationDate);
    }

    return query.getResultList();
  }
```

Um detalhe importante reside no trecho `WHERE 1=1`. É uma espécie de gambiarra para não deixar a responsabilidade de inserir o **WHERE** nas verificações de parâmetros. Caso fosse assim, seria preciso verificar todas as vezes se o **WHERE** foi inserido pela verificação anterior. A declaração `WHERE 1=1` irá sempre resultar em verdadeiro. Portanto, a consulta será executada sem erros retornando todos os registros da tabela.

Uma outra possibilidade para montar consultas dinâmicas, é o uso da API **Criteiria** da JPA. É um conjunto de especificações para criação de consultas utilizando chamadas à métodos específicos. O código abaixo é equivalante ao código acima, porém, fazendo uso desta API.

```java
  public List<Product> searchByParamsWithCriteria(String name, BigDecimal price, LocalDate creationDate) {
    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    CriteriaQuery<Product> query = builder.createQuery(Product.class);
    Root<Product> from = query.from(Product.class);

    Predicate filters = builder.and();

    if (name != null && !name.trim().isEmpty()) {
      filters = builder.and(filters, builder.equal(from.get("name"), name));
    }

    if (price != null) {
      filters = builder.and(filters, builder.equal(from.get("price"), price));
    }

    if (creationDate != null) {
      filters = builder.and(filters, builder.equal(from.get("creation_date"), creationDate));
    }

    query.where(filters);

    return this.em.createQuery(query).getResultList();
  }
```

## DAO
