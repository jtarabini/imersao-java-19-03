package br.com.targettrust.traccadastros.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.targettrust.traccadastros.entidades.Locacao;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	public Boolean veiculoEstaDisponivel(String placa, 
			Date dataInicial, Date dataFinal) {
		List<Locacao> locacoes = locacaoRepository
				.findByPlacaVeiculo(placa, dataInicial, dataFinal);
		List<Reserva> reservas = reservaRepository
				.findByPlacaVeiculo(placa, dataInicial, dataFinal);
		// Pode retornar diretamente o resultado da expressão
		if( (locacoes == null || locacoes.isEmpty()) &&
		    (reservas == null || reservas.isEmpty()) )
			return true;
		else {
			return false;
		}
	}

}
