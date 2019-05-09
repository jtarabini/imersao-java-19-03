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

}

