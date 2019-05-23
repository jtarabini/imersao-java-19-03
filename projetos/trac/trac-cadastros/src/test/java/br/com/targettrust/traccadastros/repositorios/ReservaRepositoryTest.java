package br.com.targettrust.traccadastros.repositorios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import br.com.targettrust.traccadastros.util.DateUtil;
import br.com.targettrust.traccadastros.util.TestObjectFactory;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservaRepositoryTest {
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private TestObjectFactory testObjectFactory;
	
	private Carro carro;
	
	@Before
	public void setup() {
		carro = testObjectFactory.createCarro();
		this.reservaRepository.deleteAll();
	}
	
	@Test
	public void carroSemReservaDeveRetornarVazio() {
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
		testObjectFactory.createReserva(
				carro,
				LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
				LocalDate.now().plusDays(11).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				TestObjectFactory.PLACA_DEFAULT,
				LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
				LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
		// Assert
		Assert.assertThat(reservas, Matchers.hasSize(1));
	}
	
	@Test
	public void carroComReservaForaDaDataDeveRetornarVazio() {
		testObjectFactory.createReserva(
				carro,
				LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
				LocalDate.now().plusDays(11).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
		// Act
		List<Reserva> reservas = reservaRepository.findByPlacaVeiculo(
				TestObjectFactory.PLACA_DEFAULT,
				LocalDate.now().plusDays(12).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
				LocalDate.now().plusDays(22).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
		// Assert
		Assert.assertThat(reservas, Matchers.empty());
	}

}
