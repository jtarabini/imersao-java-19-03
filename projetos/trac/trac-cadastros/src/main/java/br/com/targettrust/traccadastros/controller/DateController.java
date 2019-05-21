package br.com.targettrust.traccadastros.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Reserva;

@RestController
@RequestMapping("datas")
public class DateController {
	
	@GetMapping
	public void test(
			@RequestParam(name="date") 
			@DateTimeFormat(pattern="dd.MM.yyyy") Date date) {
		System.out.println(date);		
	}
	
	@PostMapping
	public void testReserva(@RequestBody Reserva reserva) {
		System.out.println(reserva.getDataInicial());
		
	}

}
