package br.com.targettrust.traccadastros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Locacao;

public interface LocacaoRepository 
	extends JpaRepository<Locacao, Long>{

}
