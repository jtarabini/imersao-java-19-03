package br.com.targettrust.locadora.db.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.targettrust.locadora.db.MotoRepository;
import br.com.targettrust.locadora.db.MotoRepositoryImpl;
import br.com.targettrust.locadora.entidades.Moto;
import br.com.targettrust.locadora.exception.PlacaJaCadastradaException;
import br.com.targettrust.locadora.exception.VeiculoNaoEncontradoException;

public class MotoRepositoryTest {
	
	private static final int DEFAULT_ID = 99999;
	private static final String PLACA_DEFAULT = "XXX-1234";
	private MotoRepository motoRepository = new MotoRepositoryImpl();
	
	@Before
	public void setup() {
		motoRepository.delete(PLACA_DEFAULT);
		motoRepository.delete(DEFAULT_ID);
	}
	
	@After
	public void cleanUp() {
		motoRepository.delete(PLACA_DEFAULT);		
	}
	
	@Test
	public void basicInsert() {
		// Arrange
		Moto moto = new Moto();
		moto.setAno(2001);
		moto.setCor("Prata");
		moto.setMarca("Honda");
		moto.setModelo("CG Titan");
		moto.setPlaca(PLACA_DEFAULT);
		moto.setCilindradas(150);
		// Act
		motoRepository.insert(moto);
		// Assert
		Moto motoDb = motoRepository.findByPlaca(moto.getPlaca());
		Assert.assertNotNull(motoDb);
		Assert.assertNotNull(motoDb.getId());
		// Clean-up
		motoRepository.delete(moto.getPlaca());
	}
	
	@Test(expected=PlacaJaCadastradaException.class)
	public void insertPlacaDuplicadaDeveRetornarErro() {
		// Arrange
		Moto moto = new Moto();
		moto.setAno(2001);
		moto.setCor("Prata");
		moto.setMarca("Honda");
		moto.setModelo("CG Titan");
		moto.setPlaca(PLACA_DEFAULT);
		moto.setCilindradas(150);
		motoRepository.insert(moto);
		// Act
		motoRepository.insert(moto);
		// Assert
		
	}
	
	@Test
	public void basicUpdate() {
		//Arrange
		Moto moto = new Moto();
		moto.setAno(2001);
		moto.setCor("Prata");
		moto.setMarca("Honda");
		moto.setModelo("CG Titan");
		moto.setPlaca(PLACA_DEFAULT);
		moto.setCilindradas(150);
		motoRepository.insert(moto);
		moto.setId(motoRepository.findByPlaca(moto.getPlaca()).getId());
		moto.setCor("Preto");
		//Act
		motoRepository.update(moto);
		//Assert
		Moto motoDb = motoRepository.findByPlaca(moto.getPlaca());
		Assert.assertEquals(motoDb.getCor(), moto.getCor());		
	}
	
	@Test(expected=VeiculoNaoEncontradoException.class)
	public void updateDeVeiculoInexistenteDeveLancarErro() {
		// Arrange
		Moto moto = new Moto();
		moto.setAno(2001);
		moto.setCor("Prata");
		moto.setMarca("Honda");
		moto.setModelo("CG Titan");
		moto.setPlaca(PLACA_DEFAULT);
		moto.setCilindradas(150);
		moto.setId(DEFAULT_ID);
		// Act
		motoRepository.update(moto);
		// Assert
		
	}

}
