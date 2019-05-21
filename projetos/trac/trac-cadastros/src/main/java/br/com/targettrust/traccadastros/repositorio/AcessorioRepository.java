package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.targettrust.traccadastros.entidades.Acessorio;
import br.com.targettrust.traccadastros.entidades.Marca;

import java.util.List;
import java.util.Optional;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long>{

	@Transactional
	void deleteByDescricao(String descricao);

	@Transactional
	Optional<Acessorio> findByDescricao(String descricao);

	@Query("   select marca "
			+ "  from Marca marca "
			+ " where (:id is null or marca.id = :id )"
			+ "   and (:nome is null or marca.nome = :nome )")
	List<Marca> search(
			@Param("id") Long id, 
			@Param("nome") String nome);
}
