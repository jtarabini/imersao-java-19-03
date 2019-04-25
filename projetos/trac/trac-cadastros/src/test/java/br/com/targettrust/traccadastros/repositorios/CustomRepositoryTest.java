package br.com.targettrust.traccadastros.repositorios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.repositorio.CustomRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomRepositoryTest {
	
	private static final String DEFAULT_PLACA = "PPP-9999";
	@Autowired
	private CustomRepository customRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Before
	@After
	public void setup() {
		veiculoRepository.deleteByPlaca(DEFAULT_PLACA);		
	}
	
	@Test
	public void insertCarroBasico() {
		Carro carro = new Carro();
		carro.setAno(2002);
		carro.setCor("PRETA");
		carro.setMarca("Hyunday");
		carro.setModelo("Santa Fé");
		carro.setPlaca(DEFAULT_PLACA);
		carro.setPortas(4);
		customRepository.saveCarro(carro);
	}
	
	

}
