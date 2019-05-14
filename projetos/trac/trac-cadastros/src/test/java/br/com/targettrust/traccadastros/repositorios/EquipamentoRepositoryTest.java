package br.com.targettrust.traccadastros.repositorios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.repositorio.EquipamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoRepositoryTest {
	
	private static final String DESCRICAO_DEFAULT = "Direção elétrica";
	private static final String DESCRICAO_ALTERADA = "Direção hidráulica";
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Before
	@After
	public void setup() {
		equipamentoRepository.deleteByDescricao(DESCRICAO_DEFAULT);
		equipamentoRepository.deleteByDescricao(DESCRICAO_ALTERADA);
	}
	
	@Test
	public void basicInsert() {
		Equipamento dbEquipamento = createEquipamento();
		System.out.println(dbEquipamento.getId());		
	}
	
	@Test(expected=ObjectOptimisticLockingFailureException.class)
	public void updatesConcorrentesDevemLancarErro() {
		//Arrange
		Equipamento equipamento = createEquipamento();
		// equipamento com versão antiga
		Equipamento equipamentoAntigo = 
				equipamentoRepository
				.findById(equipamento.getId()).get();
		Equipamento equipamentoNovo = 
				equipamentoRepository
				.findById(equipamento.getId()).get();
		equipamentoNovo.setDescricao(DESCRICAO_ALTERADA);
		equipamentoRepository.save(equipamentoNovo);
		//Act
		equipamentoAntigo.setDescricao(DESCRICAO_ALTERADA);
		equipamentoRepository.save(equipamentoAntigo);
		//Assert - não é necessário pois esperamos que uma 
		// exception seja lançada
		
	}
	
	private Equipamento createEquipamento() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao(DESCRICAO_DEFAULT);
		return equipamentoRepository.save(equipamento);		
	}

}
