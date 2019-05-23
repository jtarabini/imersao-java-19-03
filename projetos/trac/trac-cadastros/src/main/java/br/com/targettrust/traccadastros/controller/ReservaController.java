package br.com.targettrust.traccadastros.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.dto.ReservaDto;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.entidades.Veiculo;
import br.com.targettrust.traccadastros.exceptions.VeiculoIndisponivelException;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import br.com.targettrust.traccadastros.service.VeiculoService;

@RestController
@RequestMapping("reservas")
public class ReservaController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private VeiculoService veiculoService;
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Reserva> create(@RequestBody @Valid ReservaDto dto) { 
		List<Veiculo> veiculos = 
				veiculoRepository.findByModelo(dto.getModelo().getId());
		Veiculo veiculoDisponivel = null;
		for(Veiculo veiculo : veiculos) {
			if(veiculoService.veiculoEstaDisponivel(
					veiculo.getPlaca(), 
					dto.getDataInicial(), 
					dto.getDataFinal())) {
				veiculoDisponivel = veiculo;
				break;				
			}
		}
		if(veiculoDisponivel == null) {
			System.out.println("O veículo solicitado não está disponível nas datas");
			throw new VeiculoIndisponivelException();
		}
		else {
			Reserva reserva = new Reserva();
			reserva.setCliente(dto.getCliente());
			reserva.setDataInicial(dto.getDataInicial());
			reserva.setDataFinal(dto.getDataFinal());
			reserva.setVeiculo(veiculoDisponivel);
			return ResponseEntity.ok(reservaRepository.save(reserva));
		}
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

}
