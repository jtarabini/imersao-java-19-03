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

	@GetMapping("/desc/{nome}")
	public HttpEntity<Modelo> findBNome(@PathVariable("nome") String nome){
		Modelo modelo = modeloRepository.findByNome(nome.toUpperCase());
		if(modelo == null) {
			return ResponseEntity.ok(modelo);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		modeloRepository.deleteById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Modelo> createModelo(@Valid @RequestBody Modelo modelo) {
		if(modelo == null || modelo.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(modeloRepository.save(modelo));		
	}
	
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Modelo> updateModelo(@PathVariable("id") Long id, 
			@Valid @RequestBody Modelo modelo) {
		Optional<Modelo> dbModelo = modeloRepository.findById(id);
		if(dbModelo.isPresent()) {
			dbModelo.get().setNome(modelo.getNome());
			dbModelo.get().setVersion(modelo.getVersion());	
			modeloRepository.save(dbModelo.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}
}
