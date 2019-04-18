package br.com.targettrust.traccadastros.repositorio;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Locacao;

public interface LocacaoRepository 
	extends JpaRepository<Locacao, Long>{
	
	// Parametros nomeados são referenciados pelo alias após o :
	@Query("    from Locacao locacao " + 
	       "   where locacao.funcionario = :funcionario "+ 
		   "     and locacao.dataInicial between :dataInicial and :dataFinal "+
	       "order by locacao.dataInicial ")
	List<Locacao> findByFuncionario(
			@Param("funcionario") Funcionario funcionario,
			@Param("dataInicial") Date dataInicial,
			@Param("dataFinal") Date dataFinal);

	@Transactional
	@Modifying
	@Query(" delete from Locacao locacao " +
	       "  where locacao.id in ( "+
		   "        select id from Locacao l "+ 
	       "         where l.veiculo.placa = :placa " +
		   "       )")
	void deleteByVeiculo(@Param("placa") String placa);

}
