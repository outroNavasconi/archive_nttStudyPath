# Java

## Arquitetura do java

```mermaid
    ---
    title: Visão geral do Java
    ---
    flowchart LR
        java(App1.java)
        class(App1.class)
        jvm(Java Virtual Machine)
        win(Windows)
        lnx(Linux)
        mac(MacOS)
        
        java --> class
        class --> jvm
        jvm --> win
        jvm --> lnx
        jvm --> mac
```

Os arquivos **.java** são compilados para **.class** com o `javac`. JDK é um kit de ferramentas - bibliotecas, runtime, utilitários e etc - para preparar um ambiente de desenvolvimento java. Caso não seja necessário preparar um ambiente de desenvolvimento, é possível baixar apenas a JRE (Java Runtime Environment) para execução de aplicações criadas em Java.

## Executando Compilação Manualmente

> **IMPORTANTE**: Para executar a compilção - com o `javac` - do arquivo java, é necessário que o nome da classe seja igual ao nome do arquivo!

O processo de compilação de uma classe e execução são feitos utilizando os utilitários `javac` e `java` em um terminal, como no exemplo abaixo:

```
    $ javac [nome do arquivo].java
    $ java [nome do arquivo compilado]
```

Para executar um arquivo java diretamente, pode ser utilizado o comando `java [nome do arquivo].java`.

## Comentários

Em java, os comentários em linhas são escritos utilizando o `//` e os comentários de bloco são feitos com `/*` e `*/`.
Há também uma convenção chamada de **javadoc** para padronizar um tipo de documentação do código. Um exemplo de documentação:

```java
    /*
     * Função responsável por retornar o valor absoluto de um número inteiro
     *
     *  @param a Valor inteiro que será verificado
     *  @returns O valor absoluto do número 
     */
    public int abs(int a) {
        return a < 0 ? a * -1 : a;
    }

```

## Tipos Primitivos

#### Declaração e tamannho em memória

O java possui os seguintes tipos primitivos com os seguintes tamanhos e limites de valores:

|   Tipo  | Bytes |         Limite Inf         |         Limite Sup        | Valor Default |
|:-------:|:-----:|:--------------------------:|:-------------------------:|:-------------:|
|   byte  |   1   |            -128            |            127            |       0       |
|  short  |   2   |           -32.768          |           32.767          |       0       |
|   int   |   4   |       -2.147.483.648       |       2.147.483.647       |       0       |
|   long  |   8   | -9.223.372.036.854.775.808 | 9.223.372.036.854.775.807 |       0       |
|  float  |   4   |           1.4E-45          |        3.4028235E38       |      0,0      |
|  double |   8   |          4.9E-324          |   .7976931348623157E308   |      0,0      |
| boolean |   1   |            false           |            true           |     false     |
|   char  |   2   |                            |                           |     \u0000    |

#### Casting

O casting é uma técnica para conversão entre tipos diferentes. Um exemplo abaixo:

```java
    long d = 1000L;
    int e = (int) d;
```

No cenário acima, há uma conversão de long para int antes da atribuição à variável **e**.
Essa conversão deve ocorrer em casos onde o tamanho do tipo da variável que irá ser assinaldada é menor que o valor que está sendo atribuído.
Assim, será a atribuição acontecerá até onde for possível.

Entretanto, o casting não é possível entre alguns tipos primitivos, como conversões envolvendo `boolean` e tipos inteiros.

> ./Main.java:9: error: incompatible types: boolean cannot be converted to int
>       int a = (int) true;

Acima está um exemplo de erro que pode ocorrer ao tentar realizar uma conversão entre `boolean` e `int`.

#### String

String não é um tipo primitivo em java. É, na verdade, uma classe que encapsula um array de caracteres e ofere alguns métodos para manipulação dele.
A declaração de uma variável do tipo `String` é feita da seguinte forma:

```java
    String nome = "Fulano";
    System.out.println(nome);
```
