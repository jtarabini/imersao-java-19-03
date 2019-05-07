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

import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;

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
