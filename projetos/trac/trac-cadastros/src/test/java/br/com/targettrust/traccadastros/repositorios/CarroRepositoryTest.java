package br.com.targettrust.traccadastros.repositorios;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
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
	private static final String EQUIPAMENTO_DEFAULT = "DVD";
	private static final String PLACA_DEFAULT = "IST-8789";
	@Autowired
	private VeiculoRepository repository;
	// Necessário para injetar uma instância de repositório
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Before
	@After
	public void setup() {
		repository.deleteByPlaca(PLACA_DEFAULT);
		equipamentoRepository.deleteByDescricao(EQUIPAMENTO_DEFAULT);
	}
	
	@Test
	public void basicInsertTest() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		repository.save(carro);	
	}
	
	@Test
	public void insertComEquipamentos() {
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao(EQUIPAMENTO_DEFAULT);
		Equipamento dbEquipamento = equipamentoRepository.save(equipamento);
		Carro carro = new Carro();
		carro.setAno(2015);
		carro.setCor("Branco");
		carro.setMarca("Ford");
		carro.setModelo("Fusion");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		Carro dbCarro = this.repository.save(carro);
		
		dbCarro.setEquipamentos(new HashSet<>());
		dbCarro.getEquipamentos().add(dbEquipamento);	
		this.repository.save(dbCarro);
		
	}

}
