package br.com.targettrust.traccadastros.repositorios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import br.com.targettrust.traccadastros.util.TestObjectFactory;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;
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
	private static final int DEFAULT_ANO = 2012;
	private static final String DEFAULT_MODELO = "A200";
	private static final String DEFAULT_MARCA = "Mercedes";
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private TestObjectFactory testObjectFactory;
	
	@Test
	public void carroSemReservaDeveRetornarVazio() {
		// Arrange
		Carro carro = testObjectFactory.createCarro();
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
		Carro carro = testObjectFactory.createCarro();
		testObjectFactory.createReserva(
				carro,
				Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				PLACA_DEFAULT,
				Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// Assert
		Assert.assertThat(reservas, Matchers.hasSize(1));
	}
	
	@Test
	public void carroComReservaForaDaDataDeveRetornarVazio() {
		// Arrange
		Carro carro = testObjectFactory.createCarro();
		testObjectFactory.createReserva(
				carro,
				Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				PLACA_DEFAULT,
				Date.from(LocalDate.now().plusDays(12).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.now().plusDays(22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// Assert
		Assert.assertThat(reservas, Matchers.empty());
	}

}
