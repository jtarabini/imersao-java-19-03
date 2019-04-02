package br.com.targettrust.locadora.db.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.targettrust.locadora.db.CarroRepository;
import br.com.targettrust.locadora.db.CarroRepositoryImpl;
import br.com.targettrust.locadora.entidades.Carro;
import br.com.targettrust.locadora.exception.PlacaJaCadastradaException;
import br.com.targettrust.locadora.exception.VeiculoNaoEncontradoException;

public class CarroRepositoryTest {
	
	private static final int DEFAULT_ID = 9999;
	private static final String PLACA_DEFAULT = "PPP-1234";
	private CarroRepository carroRepository = new CarroRepositoryImpl();
	
	@Before
	public void setup() {
		carroRepository.delete(PLACA_DEFAULT);
		carroRepository.delete(DEFAULT_ID);
	}
	
	@After
	public void cleanUp() {
		carroRepository.delete(PLACA_DEFAULT);		
	}
	
	@Test
	public void basicInsert() {
		// Arrange
		Carro carro = new Carro();
		carro.setAno(2001);
		carro.setCor("Prata");
		carro.setMarca("Volkswagen");
		carro.setModelo("Gol");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		// Act
		carroRepository.insertCarro(carro);
		// Assert
		Carro carroDb = carroRepository.findByPlaca(carro.getPlaca());
		Assert.assertNotNull(carroDb);
		Assert.assertNotNull(carroDb.getId());
		// Clean-up
		carroRepository.delete(carro.getPlaca());
	}
	
	@Test(expected=PlacaJaCadastradaException.class)
	public void insertPlacaDuplicadaDeveRetornarErro() {
		// Arrange
		Carro carro1 = new Carro();
		carro1.setAno(2001);
		carro1.setCor("Prata");
		carro1.setMarca("Volkswagen");
		carro1.setModelo("Gol");
		carro1.setPlaca(PLACA_DEFAULT);
		carro1.setPortas(4);
		carroRepository.insertCarro(carro1);
		// Act
		carroRepository.insertCarro(carro1);
		// Assert
		
	}
	
	@Test
	public void basicUpdate() {
		//Arrange
		Carro carro = new Carro();
		carro.setAno(2001);
		carro.setCor("Prata");
		carro.setMarca("Volkswagen");
		carro.setModelo("Gol");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		carroRepository.insertCarro(carro);
		carro.setId(carroRepository.findByPlaca(carro.getPlaca()).getId());
		carro.setCor("Preto");
		//Act
		carroRepository.updateCarro(carro);
		//Assert
		Carro carroDb = carroRepository.findByPlaca(carro.getPlaca());
		Assert.assertEquals(carroDb.getCor(), carro.getCor());		
	}
	
	@Test(expected=VeiculoNaoEncontradoException.class)
	public void updateDeVeiculoInexistenteDeveLancarErro() {
		// Arrange
		Carro carro = new Carro();
		carro.setAno(2001);
		carro.setCor("Prata");
		carro.setMarca("Volkswagen");
		carro.setModelo("Gol");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		carro.setId(9999);
		// Act
		carroRepository.updateCarro(carro);
		// Assert
		
	}

}
