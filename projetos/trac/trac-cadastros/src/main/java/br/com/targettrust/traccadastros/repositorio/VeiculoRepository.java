package br.com.targettrust.traccadastros.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.targettrust.traccadastros.entidades.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	@Transactional
	void deleteByPlaca(String placa);
	
	Veiculo findByPlaca(String placa);
	
	@Query("                  from Veiculo veiculo"+ 
	       " left outer join fetch veiculo.acessorios acessoriosVeiculo "+
	       "            join fetch veiculo.modelo modelo "+ 
	       " left outer join fetch modelo.acessorios acessoriosModelo "+
		   "                 where veiculo.id = :id")
	Veiculo findVeiculoComAcessoriosById(@Param("id") Long id);


	@Query("delete Veiculo veiculo "+
			" where veiculo.modelo.id in( "+
			"        select id "+
			"          from Modelo "+
			"         where marca.nome = :marca )")
	@Transactional
	@Modifying
	void deleteByMarca(@Param("marca") String marca);

	@Query(" select e "
			+ "   from Veiculo e "
			+ "where e.modelo.id = :modeloId")
	List<Veiculo> findByModelo(@Param("modeloId") Long id);
}
