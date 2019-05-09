package br.com.targettrust.traccadastros.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Usuario;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public HttpEntity<List<Funcionario>> listarfuncionario() {
		return ResponseEntity.ok(usuarioRepository.findFuncionarios());		
	}
	
	@GetMapping("/{id}") 
	public HttpEntity<Funcionario> findById(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = usuarioRepository.findFuncionarioById(id);
		if(funcionario.isPresent()) {
			return ResponseEntity.ok((Funcionario) funcionario.get());
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
	public HttpEntity<Funcionario> createFuncionario(@Valid @RequestBody Funcionario funcionario) {
		if(funcionario == null || funcionario.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(usuarioRepository.save(funcionario));		
	}
	
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Funcionario> updateFuncionario(@PathVariable("id") Long id, 
			@Valid @RequestBody Marca marca) {
		Optional<Usuario> dbFuncionario = usuarioRepository.findById(id);
		if(dbFuncionario.isPresent()) {
			dbFuncionario.get().setNome(marca.getNome());
			dbFuncionario.get().setVersion(marca.getVersion());	
			usuarioRepository.save(dbFuncionario.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}

}
