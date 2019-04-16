# Configuração inicial do spring

Uma das conhecidas desvantagens do spring é a complexidade de sua  configuração inicial em vista da infinidade de módulos e bibliotecas presentes. Para facilitar a "*quickstart?*" de um projeto podemos utilizar o *[spring initializr](https://start.spring.io) spring initializr*, conforme mostra a figura abaixo:
 ![Spring initializr](../imagens/Spring_initializr.png  "Spring initializr")


Nosso primeiro exemplo será demonstrar como podemos manipular dados no banco utilizando a biblioteca spring-data-jpa.  O banco a ser utilizado é o postgres portanto o driver jdbc deve ser incluído em nossas bibliotecas
```xml
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.2.5</version>
		</dependency>
```
O próximo passo é configurar nossa conexão com o banco de dados no arquivo application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/locadora
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
```
As propriedades abaixo informam ao spring os dados de conexão com o banco, o driver jdbc a ser utilizado, além de outras configurações específicas para utilizar a sintaxe própria do banco.

## Mapeamento de entidades
Para que o spring-data faça o mapeamento correto das tabelas e suas colunas às nossas classes é preciso incluirmos algumas anotações, que chamaremos de configurações jpa da entidade. São elas:
```java
package br.com.targettrust.springtest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
```
 * @Entity - Informa ao spring-data que esta classe é uma entidade e portanto tem relação com uma tabela com seu nome no banco de dados.
  * @Id - Identificador de campo referente a *primary key* da tabela
  * @GeneratedValue - Configura a estratégia de geração automática de valor de pk na tabela. No caso acima estamos utilizando AUTO, isto é, estamos delegando ao banco a responsabilidade de gerar o valor.
   * Column - Indicador de mapeamento de coluna
   
    ## Implementação de repositórios
    
   Com o mapeamento concluído podemos criar uma interface com todos os métodos de persistência da entidade da seguinte forma:
  
 ```java
   package br.com.targettrust.springtest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{

}
  ```
O código acima permite que o spring-data carregue uma implementação da interface imediatamente na subida da aplicação, provendo todos os métodos básicos de persistência de forma automática.

## Teste básico
Os testes unitários são uma excelente alternativa para testarmos se os métodos disponibilizados pelo spring-data de fato estão trabalhando com as tabelas do banco da melhor maneira.

Para o correto carregamento do contexto spring precisamos utilizar um *runner* específico da biblioteca de teste do spring, bem como injetar a classe "alvo" do teste com a anotação *@Autowired*:

 ```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoRepositoryTest {
	
	@Autowired
	EquipamentoRepository repository;

	@Test
	public void basicInsert() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao("Câmbio Automático");
		repository.save(equipamento);
	}

}
 ```
 
 Agora basta executar o teste e verificar no banco de dados.
 
 **Tema de casa: ** Implementar todas as entidades que temos no nosso projeto inicial da locadora utilizando as anotações que aprendemos, implementar os repositórios e seus respectivos testes.
 