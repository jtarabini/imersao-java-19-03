package br.com.targettrust.traccadastros.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.dto.LocacaoDto;
import br.com.targettrust.traccadastros.entidades.Locacao;
import br.com.targettrust.traccadastros.entidades.Veiculo;
import br.com.targettrust.traccadastros.exceptions.VeiculoIndisponivelException;
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import br.com.targettrust.traccadastros.service.VeiculoService;

@RestController
@RequestMapping("locacao")
public class LocacaoController {
	
	@Autowired
	private VeiculoService veiculoService;
	@Autowired
	private LocacaoRepository locacaoRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@PutMapping
	public HttpEntity<Locacao> createLocacao(
			@RequestBody @Valid LocacaoDto dto) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(dto.getIdVeiculo());
		if(!veiculo.isPresent()) {
			throw new IllegalArgumentException();
		}
		
		if(!veiculoService.veiculoEstaDisponivel(
				dto.getIdVeiculo(), 
				dto.getDataInicial(), 
				dto.getDataFinal())) {
			throw new VeiculoIndisponivelException();			
		}
		Locacao locacao = new Locacao();
		locacao.setDataInicial(dto.getDataInicial());
		locacao.setDataFinal(dto.getDataFinal());
		locacao.setCliente(dto.getCliente());
		locacao.setFuncionario(dto.getFuncionario());		
		locacao.setVeiculo(veiculo.get());
		locacao.setValor(dto.getValor());
		return ResponseEntity.ok(locacaoRepository.save(locacao ));
	}

}
