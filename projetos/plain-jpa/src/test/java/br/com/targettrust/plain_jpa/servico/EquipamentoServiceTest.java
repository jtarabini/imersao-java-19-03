package br.com.targettrust.plain_jpa.servico;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.targettrust.plain_jpa.entidades.Equipamento;
import br.com.targettrust.plain_jpa.repositorios.EquipamentoRepository;
import br.com.targettrust.plain_jpa.service.EquipamentoService;

public class EquipamentoServiceTest {
	
	private static final String DESCRICAO_DEFAULT = "Câmbio Automático";
	
	private EquipamentoService equipamentoService;
	private EquipamentoRepository equipamentoRepository;
	private EntityManager entityManager;
	
	public EquipamentoServiceTest() {
		entityManager = Persistence
				.createEntityManagerFactory("tracPU")
				.createEntityManager();
		equipamentoService = new EquipamentoService();
		equipamentoRepository = new EquipamentoRepository(entityManager);
	}
	
	@Before
	@After
	public void setup() {
		entityManager.getTransaction().begin();
		equipamentoRepository.deleteByDescricao(DESCRICAO_DEFAULT);
		entityManager.getTransaction().commit();
	}

	@Test(expected=NoResultException.class)
	public void testTransactionRollback() {
		// Arrange
		Equipamento equipamento1 = new Equipamento();
		equipamento1.setDescricao(DESCRICAO_DEFAULT);
		Equipamento equipamento2 = new Equipamento();
		equipamento2.setDescricao(DESCRICAO_DEFAULT);
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();
		equipamentos.add(equipamento1);
		equipamentos.add(equipamento2);
		//Act
		try {
			equipamentoService.saveList(equipamentos);
		}catch (Exception e) {
			// TODO: handle exception
		}
		//Assert
		equipamentoRepository
				.findByDescricao(DESCRICAO_DEFAULT);
	}

}
