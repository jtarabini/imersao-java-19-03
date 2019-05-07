package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

	@Transactional
	void deleteByNome(String nome);

	Marca findByNome(String nome);
}
