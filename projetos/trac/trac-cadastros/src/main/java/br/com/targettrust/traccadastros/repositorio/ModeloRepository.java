package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{

	@Transactional
	void deleteByNome(String nome);

}
