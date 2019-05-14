package br.com.targettrust.traccadastros.repositorio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{

	@Transactional
	void deleteByDescricao(String descricao);

	Optional<Equipamento> findByDescricao(String descricao);

}
