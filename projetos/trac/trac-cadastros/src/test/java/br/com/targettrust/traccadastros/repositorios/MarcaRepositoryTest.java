package br.com.targettrust.traccadastros.repositorios;

import br.com.targettrust.traccadastros.entidades.Acessorio;
import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.repositorio.AcessorioRepository;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import br.com.targettrust.traccadastros.util.TestObjectFactory;
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
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarcaRepositoryTest {
	@Autowired
	private MarcaRepository repository;
	@Autowired
	private TestObjectFactory testObjectFactory;
	
	@Before
	@After
	public void setup() {
		repository.deleteAll();
	}
	
	@Test
	public void testComplexSearch() {
		Marca ford = testObjectFactory.createMarca("Ford");
		Marca bmw = testObjectFactory.createMarca("BMW");
		List<Marca> marcas = repository.find(ford.getId(), bmw.getNome());
		Assert.assertThat(marcas, Matchers.hasSize(2));
	}

}
