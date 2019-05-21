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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Acessorio;
import br.com.targettrust.traccadastros.repositorio.AcessorioRepository;

@RestController
@RequestMapping("acessorios")
public class AcessorioController {
	
	@Autowired
	private AcessorioRepository acessorioRepository;
	
	@GetMapping
	public HttpEntity<List<Acessorio>> listAll(){
		return ResponseEntity.ok(acessorioRepository.findAll());		
	}
	
	@GetMapping("/search")
	public HttpEntity<List<Acessorio>> search(
			@RequestParam(name="id", required = false) Long id, 
			@RequestParam(name="descricao", required = false) String descricao) {
		List<Acessorio> acessorios = 
				acessorioRepository.search(id, descricao);
		return acessorios == null || acessorios.isEmpty() ?
				ResponseEntity.noContent().build() : 
					ResponseEntity.ok(acessorios); 
	}

	@GetMapping("/descricao/{descricao}")
	public HttpEntity<Acessorio> findByDescricao(@PathVariable("descricao") String descricao){
		Optional<Acessorio> acessorio = acessorioRepository.findByDescricao(descricao.toUpperCase());
		if(acessorio.isPresent()) {
			return ResponseEntity.ok(acessorio.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/id/{id}")
	public HttpEntity<Acessorio> findById(@PathVariable("id") Long id){
		Optional<Acessorio> acessorio = acessorioRepository.findById(id);
		if(acessorio.isPresent()) {
			return ResponseEntity.ok(acessorio.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable("id") Long id) {
		acessorioRepository.deleteById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Acessorio> createAcessorio(@Valid @RequestBody Acessorio acessorio) {
		if(acessorio == null || acessorio.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(acessorioRepository.save(acessorio));
	}
	
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Acessorio> updateMarca(@PathVariable("id") Long id, @Valid @RequestBody Acessorio acessorio) {
		Optional<Acessorio> dbAcessorio = acessorioRepository.findById(id);
		if(dbAcessorio.isPresent()) {
			dbAcessorio.get().setDescricao(acessorio.getDescricao());
			acessorioRepository.save(dbAcessorio.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}
}

