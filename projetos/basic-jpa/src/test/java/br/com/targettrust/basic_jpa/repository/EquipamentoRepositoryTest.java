package br.com.targettrust.basic_jpa.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.targettrust.basic_jpa.entity.Equipamento;

public class EquipamentoRepositoryTest {	
	
	private static final String DEFAULT_DESCRICAO = "Eqp de Teste 1";
	private EquipamentoRepository equipamentoRepository = new EquipamentoRepository();	
	
	@Before
	@After
	public void setup() {
		equipamentoRepository.deleteByDescricao(DEFAULT_DESCRICAO);		
	}
	
	@Test
	public void insertEquipamento() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao(DEFAULT_DESCRICAO);
		equipamentoRepository.save(equipamento);
	}
	
	

}
