package br.com.targettrust.traccadastros.repositorios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.repositorio.CustomRepository;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomRepositoryTest {
	
	private static final String DEFAULT_PLACA = "PPP-9999";
	private static final int DEFAULT_ANO = 2012;
	private static final String DEFAULT_MODELO = "Santa Fé";
	private static final String DEFAULT_MARCA = "Hyunday";
	@Autowired
	private CustomRepository customRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private ModeloRepository modeloRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Before
	@After
	public void setup() {
		veiculoRepository.deleteByPlaca(DEFAULT_PLACA);	
		modeloRepository.deleteByMarca(DEFAULT_MARCA);
		marcaRepository.deleteByNome(DEFAULT_MARCA);	
	}
	
	@Test
	public void insertCarroBasico() {
		Carro carro = new Carro();
		carro.setAnoFabricacao(2002);
		carro.setCor("PRETA");
		carro.setModelo(this.createMarcaAndModelo(DEFAULT_MARCA, DEFAULT_MODELO, DEFAULT_ANO));
		carro.setPlaca(DEFAULT_PLACA);
		carro.setPortas(4);
		customRepository.saveCarro(carro);
	}
	
	private Modelo createMarcaAndModelo(String marca, String modelo, int ano) {
		Marca marcaEntity = new Marca();
		marcaEntity.setNome(marca);
		this.marcaRepository.save(marcaEntity);
		Modelo modeloEntity = new Modelo();
		modeloEntity.setAno(ano);
		modeloEntity.setMarca(marcaEntity);
		modeloEntity.setNome(modelo);
		return this.modeloRepository.save(modeloEntity);
	}
	
	

}
