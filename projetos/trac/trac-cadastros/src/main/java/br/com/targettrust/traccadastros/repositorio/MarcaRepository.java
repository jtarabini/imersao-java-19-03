package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Marca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

	@Transactional
	void deleteByNome(String nome);

	Marca findByNome(String nome);

	@Query(" select marca " +
			"  from Marca marca" +
			" where (:id is null or marca.id = :id) " +
			"    or (:nome is null or marca.nome = :nome )")
	List<Marca> find(@Param("id") Long id, @Param("nome") String nome);
}
