package br.com.targettrust.traccadastros.repositorios;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.repositorio.EquipamentoRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroRepositoryTest {
	private static final String PLACA_INVALIDA_MAXIMA_DEFAULT = "NULL-0000";
	private static final String PLACA_INVALIDA_MINIMA_DEFAULT = "NUL0000";
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
		repository.deleteByPlaca(PLACA_INVALIDA_MINIMA_DEFAULT);
		repository.deleteByPlaca(PLACA_INVALIDA_MAXIMA_DEFAULT);
		equipamentoRepository.deleteByDescricao(EQUIPAMENTO_DEFAULT);
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testaPlacaInvalidaMinima() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPlaca(PLACA_INVALIDA_MINIMA_DEFAULT);
		carro.setPortas(4);
		repository.save(carro);	
	}

	@Test(expected=TransactionSystemException.class)
	public void testaPlacaInvalidaMaxima() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPlaca(PLACA_INVALIDA_MAXIMA_DEFAULT);
		carro.setPortas(4);
		repository.save(carro);	
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
	
	@Test(expected=TransactionSystemException.class)
	public void salvaPortasNull() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPortas(null);
		carro.setPlaca(PLACA_DEFAULT);
		repository.save(carro);	
	}
	
	@Test
	@Ignore
	public void salvaPortasNegativa() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setCor("Prata");
		carro.setMarca("Mercedes");
		carro.setModelo("C180");
		carro.setPortas(-1);
		carro.setPlaca(PLACA_DEFAULT);
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
	
	@Test
	@Ignore
	public void insertCarrosComEquipamentos() {
		Set<Equipamento> equipamentos = new HashSet<>();
		for(int i=0; i< 30; i++) {
			Equipamento equipamento = new Equipamento();
			equipamento.setDescricao(EQUIPAMENTO_DEFAULT+i);
			Equipamento dbEquipamento = equipamentoRepository.save(equipamento);
			equipamentos.add(dbEquipamento);
		}
		Carro carro = new Carro();
		carro.setAno(2015);
		carro.setCor("Branco");
		carro.setMarca("Ford");
		carro.setModelo("Fusion");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		Carro dbCarro = this.repository.save(carro);
		
		dbCarro.setEquipamentos(equipamentos);	
		this.repository.save(dbCarro);
		
		dbCarro = (Carro) this.repository.findVeiculoComEquipamentosById(dbCarro.getId());
		System.out.println(dbCarro);
		dbCarro.getEquipamentos();
	}

}
