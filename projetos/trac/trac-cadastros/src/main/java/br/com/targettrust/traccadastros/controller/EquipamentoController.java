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

import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.repositorio.EquipamentoRepository;

@RestController
@RequestMapping("equipamentos")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@GetMapping
	public HttpEntity<List<Equipamento>> listAll(){
		return ResponseEntity.ok(equipamentoRepository.findAll());		
	}
	
	@GetMapping("/{id}")
	public HttpEntity<Equipamento> findById(@PathVariable("id") Long id){
		Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
		if(equipamento.isPresent()) {
			return ResponseEntity.ok(equipamento.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

	@GetMapping("/desc/{descricao}")
	public HttpEntity<Equipamento> findByDescricao(@PathVariable("descricao") String descricao){
		Optional<Equipamento> equipamento = equipamentoRepository.findByDescricao(descricao.toUpperCase());
		if(equipamento.isPresent()) {
			return ResponseEntity.ok(equipamento.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
