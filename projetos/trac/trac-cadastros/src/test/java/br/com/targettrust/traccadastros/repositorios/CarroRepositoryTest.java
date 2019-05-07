package br.com.targettrust.traccadastros.repositorios;

import br.com.targettrust.traccadastros.entidades.Acessorio;
import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.repositorio.AcessorioRepository;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroRepositoryTest {
	private static final int DEFAULT_ANO = 2012;
	private static final String DEFAULT_MODELO = "C180";
	private static final String DEFAULT_MARCA = "Mercedes";
	private static final String ACESSORIO_DEFAULT = "DVD";
	private static final String PLACA_DEFAULT = "IST-8789";
	@Autowired
	private VeiculoRepository repository;
	// Necessário para injetar uma instância de repositório
	@Autowired
	private AcessorioRepository acessorioRepository;
	@Autowired
	private ModeloRepository modeloRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Before
	@After
	public void setup() {
		repository.deleteByMarca(DEFAULT_MARCA);
		acessorioRepository.deleteByDescricao(PLACA_DEFAULT);
		modeloRepository.deleteByMarca(DEFAULT_MARCA);
		marcaRepository.deleteByNome(DEFAULT_MARCA);
	}
	
	@Test
	public void basicInsertTest() {
		Carro carro = createCarroInstance();
		carro.setPortas(4);
		repository.save(carro);	
	}

	@Test(expected=TransactionSystemException.class)
	public void salvaPortasNull() {
		Carro carro = createCarroInstance();
		carro.setPortas(null);
		repository.save(carro);	
	}
	
	@Test(expected = TransactionSystemException.class)
	public void salvaPortasNegativa() {
		Carro carro = createCarroInstance();
		carro.setPortas(-1);
		repository.save(carro);	
	}
	
	@Test
	public void insertComAcessorios() {
		Acessorio acessorio = new Acessorio();
		acessorio.setDescricao(ACESSORIO_DEFAULT);
		Acessorio dbAcessorio = acessorioRepository.save(acessorio);
		Carro carro = this.createCarroInstance();
		Carro dbCarro = this.repository.save(carro);
		
		dbCarro.setAcessorios(new HashSet<>());
		dbCarro.getAcessorios().add(dbAcessorio);	
		this.repository.save(dbCarro);		
	}
	
	@Test
	public void insertCarrosComAcessorios() {
		Set<Acessorio> acessorios = new HashSet<>();
		for(int i=0; i< 30; i++) {
			Acessorio acessorio = new Acessorio();
			acessorio.setDescricao(ACESSORIO_DEFAULT+i);
			Acessorio dbAcessorio = acessorioRepository.save(acessorio);
			acessorios.add(dbAcessorio);
		}
		Carro carro = this.createCarroInstance();
		Carro dbCarro = this.repository.save(carro);
		
		dbCarro.setAcessorios(acessorios);	
		this.repository.save(dbCarro);
		
		dbCarro = (Carro) this.repository.findVeiculoComAcessoriosById(dbCarro.getId());
		Assert.assertThat(dbCarro.getAcessorios(), Matchers.not(Matchers.empty()));	
	}

	private Carro createCarroInstance() {
		Carro carro = new Carro();
		carro.setAnoFabricacao(DEFAULT_ANO);
		carro.setCor("Prata");
		carro.setModelo(this.createMarcaAndModelo(DEFAULT_MARCA, DEFAULT_MODELO, DEFAULT_ANO));
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		return carro;
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
