package br.com.targettrust.springtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoRepositoryTest {
	
	@Autowired
	EquipamentoRepository repository;

	@Test
	public void basicInsert() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao("Câmbio Automático");
		repository.save(equipamento);
	}

}
