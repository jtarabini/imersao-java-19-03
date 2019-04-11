package br.com.targettrust.traccadastros.repositorios;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.repositorio.EquipamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipamentoRepositoryTest {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Test
	public void basicInsert() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao("Direção elétrica");
		Equipamento dbEquipamento = equipamentoRepository.save(equipamento);
		System.out.println(dbEquipamento.getId());
		
	}

}
