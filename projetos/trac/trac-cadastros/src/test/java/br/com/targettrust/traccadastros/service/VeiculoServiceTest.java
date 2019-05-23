package br.com.targettrust.traccadastros.service;

import java.time.LocalDate;
import java.time.ZoneId;

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
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import br.com.targettrust.traccadastros.util.TestObjectFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private TestObjectFactory testObjectFactory;

    @Before
    @After
    public void setup() {
        this.reservaRepository.deleteAll();
        this.locacaoRepository.deleteAll();
    }

    @Test
    public void testSemLocacoesEReservas() {
        // Arrange
        //Criando locações
        Funcionario funcionario = testObjectFactory.createFuncionario();
        Cliente cliente = testObjectFactory.createCliente();
        Carro carro = testObjectFactory.createCarro();

        testObjectFactory.createLocacao(funcionario, cliente, carro,
                LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
        testObjectFactory.createReserva(
                carro,
                LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now().plusDays(12).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
        //Act
        Boolean disponivel = veiculoService.veiculoEstaDisponivel(carro.getPlaca(),
                LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
        //Assert
        Assert.assertTrue(disponivel);
    }

    // TODO - testar situações com e sem locações e reserva

}
