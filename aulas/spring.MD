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
* [Spring-data](./spring-data-jpa.MD) Spring-data