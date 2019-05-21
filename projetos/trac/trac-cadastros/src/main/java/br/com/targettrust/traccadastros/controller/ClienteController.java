@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public HttpEntity<List<Cliente>> listarCliente() {
		return ResponseEntity.ok(usuarioRepository.findClientes());		
	}
	
	@GetMapping("/{id}") 
	public HttpEntity<Cliente> findById(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = usuarioRepository.findClienteById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok((Cliente) cliente.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
		if(cliente == null || cliente.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(usuarioRepository.save(cliente));		
	}
	
	@PostMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Cliente> updateCliente(@PathVariable("id") Long id, 
			@Valid @RequestBody Cliente cliente) {
		Optional<Cliente> dbCliente = usuarioRepository.findById(id);
		if(dbCliente.isPresent()) {
			dbCliente.get().setNome(cliente.getNome());
			dbCliente.get().setVersion(cliente.getVersion());	
			usuarioRepository.save(dbCliente.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}
	
	

}
© 2019 GitHub, Inc.
