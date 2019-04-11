package br.com.targettrust.traccadastros.repositorios;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.repositorio.EquipamentoRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroRepositoryTest {
	@Autowired
	private VeiculoRepository repository;
	// Necessário para injetar uma instância de repositório
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Test
	public void basicInsertTest() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPlaca("IST-8789");
		carro.setPortas(4);
		repository.save(carro);			
	}
	
	@Test
	public void insertComEquipamentos() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao("DVD");
		Equipamento dbEquipamento = equipamentoRepository.save(equipamento);
		Carro carro = new Carro();
		carro.setAno(2015);
		carro.setCor("Branco");
		carro.setMarca("Ford");
		carro.setModelo("Fusion");
		carro.setPlaca("XYZ-9876");
		carro.setPortas(4);
		Carro dbCarro = this.repository.save(carro);	
		
		dbCarro.setEquipamentos(new HashSet<>());
		dbCarro.getEquipamentos().add(dbEquipamento);	
		this.repository.save(dbCarro);
		
	}

}
