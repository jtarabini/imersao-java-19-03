package br.com.targettrust.traccadastros.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.util.TestObjectFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private TestObjectFactory testObjectFactory;

    @Test
    public void testSemLocacoesEReservas() {
        // Arrange
        //Criando locações
        Funcionario funcionario = testObjectFactory.createFuncionario();
        Cliente cliente = testObjectFactory.createCliente();
        Carro carro = testObjectFactory.createCarro();

        testObjectFactory.createLocacao(funcionario, cliente, carro,
                Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        testObjectFactory.createReserva(
                carro,
                Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.now().plusDays(12).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //Act
        Boolean disponivel = veiculoService.veiculoEstaDisponivel(carro.getPlaca(),
                Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.now().plusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //Assert
        Assert.assertTrue(disponivel);
    }

    // TODO - testar situações com e sem locações e reserva

}
