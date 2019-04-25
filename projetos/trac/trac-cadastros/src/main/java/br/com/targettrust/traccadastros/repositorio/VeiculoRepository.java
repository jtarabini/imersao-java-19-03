package br.com.targettrust.traccadastros.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.targettrust.traccadastros.entidades.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	@Transactional
	void deleteByPlaca(String placa);
	
	@Query(" from Veiculo veiculo"+ 
	       " join fetch veiculo.equipamentos equipamento "+ 
		   " where veiculo.id = :id")
	Veiculo findVeiculoComEquipamentosById(@Param("id") Long id);

}
