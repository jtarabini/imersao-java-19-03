package br.com.targettrust.plain_jpa.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.targettrust.plain_jpa.entidades.Equipamento;
import br.com.targettrust.plain_jpa.repositorios.EquipamentoRepository;

public class EquipamentoRepositoryTest {
	
	private static final String DESCRICAO_DEFAULT = "Direção elétrica";
	private static final String DESCRICAO_ALTERADA = "Direção hidráulica";
	private EquipamentoRepository equipamentoRepository;
	private EntityManager entityManager;
	
	public EquipamentoRepositoryTest() {
		entityManager = Persistence
				.createEntityManagerFactory("tracPU")
				.createEntityManager();
		equipamentoRepository = new EquipamentoRepository(entityManager);
	}

	@Before
	@After
	public void setup() {
		entityManager.getTransaction().begin();
		equipamentoRepository.deleteByDescricao(DESCRICAO_DEFAULT);
		equipamentoRepository.deleteByDescricao(DESCRICAO_ALTERADA);
		entityManager.getTransaction().commit();
	}

	
	@Test
	public void basicInsert() {
		Equipamento dbEquipamento = createEquipamento();
		System.out.println(dbEquipamento.getId());		
	}
	/*
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
	*/
	
	private Equipamento createEquipamento() {
		entityManager.getTransaction().begin();
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao(DESCRICAO_DEFAULT);
		entityManager.getTransaction().commit();	
		return equipamentoRepository.save(equipamento);	
	}

}
