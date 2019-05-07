package br.com.targettrust.traccadastros.repositorios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Locacao;
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import br.com.targettrust.traccadastros.util.TestObjectFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocacaoRepositoryTest {
	@Autowired
	private LocacaoRepository locacaoRepository;
	@Autowired
	private TestObjectFactory testObjectFactory;

	@Test
	public void comDuasLocacoesNoMesDeveRetornarDuasNaConsulta(){
		//Arrange
		Funcionario funcionario = testObjectFactory.createFuncionario();
		Cliente cliente = testObjectFactory.createCliente();
		Carro carro = testObjectFactory.createCarro();
		testObjectFactory.createLocacao(funcionario, cliente, carro,
				Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		testObjectFactory.createLocacao(funcionario, cliente, carro,
				Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		testObjectFactory.createLocacao(funcionario, cliente, carro,
				Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		//Act
		List<Locacao> locacoes = locacaoRepository
				.findByFuncionario(funcionario,
						Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()),
						Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		//Assert
		Assert.assertThat(locacoes, Matchers.notNullValue());
		Assert.assertThat(locacoes.size(), Matchers.equalTo(2));
		
	}
}
