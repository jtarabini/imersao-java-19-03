package br.com.targettrust.traccadastros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.targettrust.traccadastros.entidades.Veiculo;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;
	@Autowired
	Veiculo veiculo;
	
	
	public Veiculo veiculoTemPortas(String placa) {
		
		
		return veiculo;
		
	}
}
