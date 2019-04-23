package br.com.targettrust.traccadastros.repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import br.com.targettrust.traccadastros.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocacaoRepositoryTest {
	
	private static final String PLACA_DEFAULT = "IXX-9I99";
	@Autowired
	private LocacaoRepository locacaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Before
	@After
	public void cleanup() {
		locacaoRepository.deleteByVeiculo(PLACA_DEFAULT);
		veiculoRepository.deleteByPlaca(PLACA_DEFAULT);
	}

	@Test
	public void comDuasLocacoesNoMesDeveRetornarDuasNaConsulta(){
		//Arrange
		/*
		// Isto não é um boa prática, estou fazendo a pedidos 
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin("administrador");
		funcionario.setMatricula("12345678");
		funcionario.setNome("Administrador do Sistema");
		funcionario.setSenha("1q2w3e");
		funcionario.setId(usuarioRepository.save(funcionario).getId());
		Cliente cliente = new Cliente();
		cliente.setEndereco("Rua São Franciso da Califórnia, 23");
		cliente.setNome("Thiago Valverde de Souza");
		cliente.setLogin("valverde.thiago");
		cliente.setSenha("target@2019");
		cliente.setId(usuarioRepository.save(cliente).getId());
		Carro carro = new Carro();
		carro.setMarca("Audi");
		carro.setModelo("A5");
		carro.setAno(2019);
		carro.setPlaca(PLACA_DEFAULT);
		carro.setCor("Branca");
		carro.setPortas(4);
		carro.setId(veiculoRepository.save(carro).getId());
		Locacao locacao = new Locacao();
		locacao.setCliente(cliente);
		locacao.setVeiculo(carro);
		locacao.setFuncionario(funcionario);
		Date dataInicial = new Date();
		Date dataFinal = new Date();
		locacao.setDataInicial(dataInicial);
		locacao.setDataFinal(dataFinal);
		locacao.setId(locacaoRepository.save(locacao).getId());
		//Act
		List<Locacao> locacoes = locacaoRepository
				.findByFuncionario(funcionario, dataInicial, dataFinal);
		//Assert TODO
		System.out.println(locacoes);
		*/

		Funcionario funcionario = createFuncionario();
		Cliente cliente = createCliente();
		Carro carro = createCarro();
		createLocacao(funcionario, cliente, carro, 
				DateUtil.createDate("01/01/2019 14:00"), 
				DateUtil.createDate("03/01/2019 12:00"));
		createLocacao(funcionario, cliente, carro, 
				DateUtil.createDate("05/01/2019 14:00"), 
				DateUtil.createDate("06/01/2019 12:00"));
		createLocacao(funcionario, cliente, carro, 
				DateUtil.createDate("05/02/2019 14:00"), 
				DateUtil.createDate("06/02/2019 12:00"));
		//Act
		List<Locacao> locacoes = locacaoRepository
				.findByFuncionario(funcionario, 
						DateUtil.createDate("01/01/2019 00:00"), 
						DateUtil.createDate("31/01/2019 23:59"));
		//Assert TODO
		Assert.assertThat(locacoes, Matchers.notNullValue());
		Assert.assertThat(locacoes.size(), Matchers.equalTo(2));
		
	}
	

	private void createLocacao(Funcionario funcionario, Cliente cliente, Carro carro, Date dataInicial,
			Date dataFinal) {
		Locacao locacao = new Locacao();
		locacao.setCliente(cliente);
		locacao.setVeiculo(carro);
		locacao.setFuncionario(funcionario);
		locacao.setDataInicial(dataInicial);
		locacao.setDataFinal(dataFinal);
		locacao.setId(locacaoRepository.save(locacao).getId());
	}

	private Carro createCarro() {
		Carro carro = new Carro();
		carro.setMarca("Audi");
		carro.setModelo("A5");
		carro.setAno(2019);
		carro.setPlaca(PLACA_DEFAULT);
		carro.setCor("Branca");
		carro.setPortas(4);
		carro.setId(veiculoRepository.save(carro).getId());
		return carro;
	}

	private Cliente createCliente() {
		Cliente cliente = new Cliente();
		cliente.setEndereco("Rua São Franciso da Califórnia, 23");
		cliente.setNome("Thiago Valverde de Souza");
		cliente.setLogin("valverde.thiago");
		cliente.setSenha("target@2019");
		cliente.setId(usuarioRepository.save(cliente).getId());
		return cliente;
	}

	private Funcionario createFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin("administrador");
		funcionario.setMatricula("12345678");
		funcionario.setNome("Administrador do Sistema");
		funcionario.setSenha("1q2w3e");
		funcionario.setId(usuarioRepository.save(funcionario).getId());
		return funcionario;
	}
}
