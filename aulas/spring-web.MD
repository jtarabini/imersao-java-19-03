# Spring-WEB

Conforme vimos nas últimas aulas, do modelo MVC nos falta apenas a última camada, a de controller. 
Nas aplicações com arquiteturas mais modernas e escaláveis, seja para aplicações com interface com o usuário e aplicações apenas com serviços, o modelo de comunicação adotado quase sempre é o REST, visto na última aula. Agora veremos como fazemos para implementar uma API Rest utilizando spring-web.

## Dependência
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```

## Implementando a primeira API

O próximo passo é implementar um controller para nosso primeiro recurso. Para mantermos a simplicidade no primeiro exemplo o recurso escolhido será a entidade **Marca**.

REST Controllers em spring são implementações de classes Java convencionais com a *annotation* **@RestController**. Cada REST controller será mapeado a um recurso através da *annotaion* **@RequestMapping**, assim como no exemplo abaixo:

```java
@RestController
@RequestMapping("marcas")
public class MarcaController {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@GetMapping
	public HttpEntity<List<Marca>> listAll() {
		return ResponseEntity.ok(marcaRepository.findAll());		
	}
	
	@GetMapping("/{id}")
	public HttpEntity<Marca> findById(@PathVariable("id") Long id) {
		Optional<Marca> marca = marcaRepository.findById(id);
		if(marca.isPresent()) {
			return ResponseEntity.ok(marca.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
```

No exemplo acima possuimos um repositório injetado no controller (marcaRepository) e dois métodos:
* list - Lista todas as marcas cadastradas
* findById - Busca uma determinada marca a partir de um ID.

Reparem que para ambos os métodos utilizamos a *annotaion* **@GetMapping**. Essa anotação representa o mapeamento deste método com o verbo HTTP GET. Logo quando uma requisição for feita para o recurso **marcas** utilizando o verbo GET e sem nenhum parâmetro no path, o método a ser chamado será o list.

## Tratando retorno da API
Como já vimos APIs REST devem ser desenhadas para retornar ao usuário uma resposta, que pode ser vazia, e um código de resultado da operação invocada. No Spring a classe responsável por encapsular esses atributos é a HttpEntity. 

Juntamente com essa classe o Spring também disponibiliza um método utilitário para construção de retornos HttpEntity, o ResponseEntity. Nele podemos encontrar métodos úteis para retornar objetos prontos com código de erro ok, ou até mesmo mensagens customizadas com códigos de erro distintos.
 