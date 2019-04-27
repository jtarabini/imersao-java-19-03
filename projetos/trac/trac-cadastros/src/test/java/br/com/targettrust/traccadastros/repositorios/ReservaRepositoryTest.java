package br.com.targettrust.traccadastros.repositorios;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import br.com.targettrust.traccadastros.util.DateUtil;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservaRepositoryTest {
	
	private static final String ADMINISTRADOR_LOGIN = "administrador";
	private static final String CLIENTE_LOGIN = "target.trust";
	private static final String PLACA_DEFAULT = "HHH-9898";
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Before
	@After
	public void setup() {
		this.reservaRepository.deleteByPlaca(PLACA_DEFAULT);
		this.veiculoRepository.deleteByPlaca(PLACA_DEFAULT);
		this.usuarioRepository
			.deleteClienteByLogin(CLIENTE_LOGIN);
		this.usuarioRepository
			.deleteFuncionarioByLogin(ADMINISTRADOR_LOGIN);
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testaDataInicioReservaInvalidaNoPassado() {
		Carro carro = createCarro();
		Reserva reserva = createReserva(
				carro, 
				DateUtil.createDate("01/01/2019 12:00"),
				DateUtil.createDate("10/05/2019 14:00"));
	}

	@Test(expected=TransactionSystemException.class)
	public void testaDataFinalReservaInvalidaNoPassado() {
		Carro carro = createCarro();
		Reserva reserva = createReserva(
				carro, 
				DateUtil.createDate("01/05/2019 12:00"),
				DateUtil.createDate("01/01/2019 14:00"));
	}
	@Test
	public void carroSemReservaDeveRetornarVazio() {
		// Arrange
		Carro carro = createCarro();
		// Act
		List<Reserva> reservas = reservaRepository
				.findByPlacaVeiculo(carro.getPlaca(),
						DateUtil.createDate("01/05/2019 12:00"),
						DateUtil.createDate("10/05/2019 14:00"));
		// Assert
		Assert.assertThat(reservas, Matchers.empty());		
	}
	
	@Test
	public void carroComReservaDeveRetornarUmaReserva() {
		// Arrange
		Carro carro = createCarro();
		Reserva reserva = createReserva(
				carro, 
				DateUtil.createDate("01/05/2019 12:00"),
				DateUtil.createDate("10/05/2019 14:00"));
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				PLACA_DEFAULT, 
				DateUtil.createDate("02/05/2019 00:00"), 
				DateUtil.createDate("09/05/2019 23:59"));
		// Assert
		Assert.assertThat(reservas, Matchers.hasSize(1));
	}
	
	@Test
	public void carroComReservaForaDaDataDeveRetornarVazio() {
		// Arrange
		Carro carro = createCarro();
		Reserva reserva = createReserva(
				carro, 
				DateUtil.createDate("01/05/2019 12:00"),
				DateUtil.createDate("10/05/2019 14:00"));
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				PLACA_DEFAULT, 
				DateUtil.createDate("12/05/2019 00:00"), 
				DateUtil.createDate("19/05/2019 23:59"));
		// Assert
		Assert.assertThat(reservas, Matchers.empty());
	}

	private Reserva createReserva(Carro carro, Date dataInicial, Date dataFinal) {
		Reserva reserva = new Reserva();
		reserva.setDataInicial(dataInicial);
		reserva.setDataFinal(dataFinal);
		reserva.setVeiculo(carro);
		reserva.setFuncionario(createFuncionario());
		reserva.setCliente(createCliente());
		return this.reservaRepository.save(reserva);
	}

	private Cliente createCliente() {
		Cliente cliente = new Cliente();
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setLogin(CLIENTE_LOGIN);
		cliente.setNome("Cliente Target");
		cliente.setSenha("1q2w3e");
		return usuarioRepository.save(cliente);
	}

	private Funcionario createFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(ADMINISTRADOR_LOGIN);
		funcionario.setMatricula("XPTO");
		funcionario.setNome("Administrador do Sistema");
		funcionario.setSenha("qwerty");
		return usuarioRepository.save(funcionario);
	}

	private Carro createCarro() {
		Carro carro = new Carro();
		carro.setAno(2012);
		carro.setMarca("Mercedes");
		carro.setCor("Branca");
		carro.setModelo("A200");
		carro.setPlaca(PLACA_DEFAULT);
		carro.setPortas(4);
		return veiculoRepository.save(carro);
	}

}
