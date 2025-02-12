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
