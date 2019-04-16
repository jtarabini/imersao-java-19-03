package br.com.targettrust.traccadastros.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	@Transactional
	void deleteByPlaca(String placa);

}
