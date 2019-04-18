package br.com.targettrust.traccadastros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Reserva;

public interface ReservaRepository 
	extends JpaRepository<Reserva, Long>{

}
