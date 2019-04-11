# Spring

Spring é um dos frameworks baseados em Java mais populares do mercado. Sua primeira versão foi disponibilizada em 2003 e desde então é uma das ferramentas mais adotadas em projetos Java. É um framework *opensource* leve que provê estruturas muito bem definidas para suportar o desenvolvimento de aplicações em Java. 
Em outras palavras podemos dizer que o Spring lida com a infraestrutura necessária enquanto nós podemos focar nas regras de negócio da nossa aplicação. 

## Principais funcionalidades
* ***Inversion of Control*** - Padrão de desenvolvimento de software onde a sequência de chamadas de métodos é invertida em relação a programação tradicional, ou seja, não é definida diretamente pelo programador. Este controle é delegado ao que chamamos de *spring context*.

* ***Dependency Injection*** - Nome dado a funcionalidade de injetar instâncias de classes spring em outras instâncias sem conhecer de fato sua implementação fazendo o código mais desacoplado possível.

* ***Configuration Management*** - O spring trabalha com padrões de configuração de software a partir de arquivos (*resources*) que podem ser alterados facilmente sem a necessidade de recompilação (application.properties). Além disso, é possível sobescrever cada propriedade de acordo com o ambiente (dev, test, homolog e prod).
 
## Módulos
Além dos módulos de *Inversion of Control* e *Dependency Injection* o spring possui ainda:
* Programação orientada a aspectos
* *Model View Controller*
* Gerenciamento de transações
* Spring-data

As principais funcionalidades do Maven são: 
* Criação e configuração simples de projeto seguindo boas práticas: *convention over configuration* - o Maven tenta evitar ao máximo configurações desnecessárias e tenta adotar padrões;
* Gerenciamento de dependências - Inclui autalização automática, download e validação de compatibilidade;
* Isolamento entre dependências de projetos e plugins: com Maven, as dependências de projetos são recuperadas dos repositórios de dependências e os plugins são recuperados de repositórios específicos reduzindo assim conflitos de dependências;
* Sistema de repositório central - dependências de projetos podem ser carregadas do sistema de arquivos local ou de um repositório central, seja ele público ou privado.
 
## *Project Oriented Model* (POM)
A configuração de um projeto Maven é feito através de um Project Oriented Model, representado pelo arquivo pom.xml. O POM descreve o projeto, gerencia suas dependências e configura plugins para a construção do software. 
O POM também define relacionamentos entre os móculos de projetos multi-módulos. Vamos olhar a estrutura básica de um típico arquivo POM:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>br.com.targettrust</groupId>
  <artifactId>trac-cadastros</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>trac-cadastros</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```
## Identificadores de projeto
Utilizamos um conjunto de identificadores, também chamados de coordenadas, para identificar unicamente um projeto e especificar como o mesmo deve ser empacotado:
* groupId - Um nome único baseado na empresa ou grupo que criou o projeto
* artifactId - UM nome único para o projeto
* version - Versão do projeto/artefato
* packaging - Modelo de empacotamento (JAR/WAR/ZIP/EAR)

## Dependências
Bibliotecas externas utilizadas pelo projeto são chamadas dependências. A funcionalidade de gerenciamento de dependências do maven assegura o download automático de bibliotecas de um repositório central, para que você não peciso armazená-las localmente. 
Para declarar uma dependẽncia a uma biblioteca externa precisamos prover os identificadores (*groupId, artifactId, version*) assim como o escopo (*scope*)
```xml
...

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
...
```
Escopos de dependências podem ser os seguintes:
* compile - escopo padrão, será utilizado caso nenhum outro seja especificado. Dependências nesse escopo estão disponíveis em todos os *classpaths* do projeto e são propagadas a todos os projetos dependentes deste;
* provided - Similar ao escopo *compile*, porém indica a JDK que é provida pelo ambientede execução;
* runtime - Indica que a dependência não é exigida para compilação, somente para execução;
* test - Indica que somente classes de teste terão acesso a esta dependência
* system - Indicaque a dependência é *provided* mas que não pode ser recuperada de um repositório e sim do sistema de arquivos local