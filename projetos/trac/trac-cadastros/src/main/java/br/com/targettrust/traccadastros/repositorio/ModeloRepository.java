package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Modelo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{

	@Transactional
	void deleteByNome(String nome);
	
	Modelo findByNome(String nome);

	@Query("delete Modelo modelo "+
			" where modelo.id in( "+
			"        select id "+
			"          from Modelo "+
			"         where marca.nome = :marca )")
	@Transactional
	@Modifying
    void deleteByMarca(@Param("marca") String marca);
}
