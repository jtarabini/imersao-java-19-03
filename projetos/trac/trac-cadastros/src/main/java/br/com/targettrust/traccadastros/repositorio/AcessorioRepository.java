package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Acessorio;

import java.util.Optional;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long>{

	@Transactional
	void deleteByDescricao(String descricao);

	@Transactional
	Optional<Acessorio> findByDescricao(String descricao);

}
