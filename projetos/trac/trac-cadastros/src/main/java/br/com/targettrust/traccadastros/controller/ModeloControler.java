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

import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;

@RestController
@RequestMapping("modelos")
public class ModeloController {
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@GetMapping
	public HttpEntity<List<Modelo>> listAll() {
		return ResponseEntity.ok(modeloRepository.findAll());		
	}
	
	@GetMapping("/{id}")
	public HttpEntity<Modelo> findById(@PathVariable("id") Long id) {
		Optional<Modelo> modelo = modeloRepository.findById(id);
		if(modelo.isPresent()) {
			return ResponseEntity.ok(modelo.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
