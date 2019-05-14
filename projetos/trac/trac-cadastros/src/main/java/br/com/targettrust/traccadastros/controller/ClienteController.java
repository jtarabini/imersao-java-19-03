package br.com.targettrust.traccadastros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Usuario;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;


@RestController
@RequestMapping ("cliente")
public class ClienteController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public HttpEntity<List<Usuario>> listAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());		
	}
	@GetMapping("/{id}")
	public HttpEntity<Usuario> findById(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
