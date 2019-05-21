package br.com.targettrust.traccadastros.controller;

import org.springframework.http.MediaType;
import java.util.Optional;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Usuario;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;

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
		Optional<Usuario> dbCliente = usuarioRepository.findById(id);
		if(dbCliente.isPresent()) {
			dbCliente.get().setNome(cliente.getNome());
			dbCliente.get().setVersion(cliente.getVersion());	
			usuarioRepository.save(dbCliente.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}
}

