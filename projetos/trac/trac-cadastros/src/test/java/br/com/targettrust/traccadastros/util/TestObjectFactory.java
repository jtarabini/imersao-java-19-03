package br.com.targettrust.traccadastros.util;

import br.com.targettrust.traccadastros.entidades.*;
import br.com.targettrust.traccadastros.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestObjectFactory {


    public static final String PLACA_DEFAULT = "IXX-9I99";
    public static final int DEFAULT_ANO = 2012;
    public static final String DEFAULT_MODELO = "A5";
    public static final String DEFAULT_MARCA = "Audi";

    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private MarcaRepository marcaRepository;


    public Reserva createReserva(Carro carro, Date dataInicial, Date dataFinal) {
        Reserva reserva = new Reserva();
        reserva.setDataInicial(dataInicial);
        reserva.setDataFinal(dataFinal);
        reserva.setVeiculo(carro);
        reserva.setFuncionario(createFuncionario());
        reserva.setCliente(createCliente());
        return this.reservaRepository.save(reserva);
    }

    public void createLocacao(Funcionario funcionario, Cliente cliente, Carro carro, Date dataInicial,
                               Date dataFinal) {
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(carro);
        locacao.setFuncionario(funcionario);
        locacao.setDataInicial(dataInicial);
        locacao.setDataFinal(dataFinal);
        locacao.setId(locacaoRepository.save(locacao).getId());
    }

    public Carro createCarro() {
        Carro carro = new Carro();
        carro.setModelo(this.createMarcaAndModelo(DEFAULT_MARCA, DEFAULT_MODELO, DEFAULT_ANO));
        carro.setAnoFabricacao(2019);
        carro.setPlaca(PLACA_DEFAULT);
        carro.setCor("Branca");
        carro.setPortas(4);
        carro.setId(veiculoRepository.save(carro).getId());
        return carro;
    }

    public Modelo createMarcaAndModelo(String marca, String modelo, int ano) {
        Marca marcaEntity = new Marca();
        marcaEntity.setNome(marca);
        this.marcaRepository.save(marcaEntity);
        Modelo modeloEntity = new Modelo();
        modeloEntity.setAno(ano);
        modeloEntity.setMarca(marcaEntity);
        modeloEntity.setNome(modelo);
        return this.modeloRepository.save(modeloEntity);
    }

    public Cliente createCliente() {
        Cliente cliente = new Cliente();
        cliente.setEndereco("Rua São Franciso da Califórnia, 23");
        cliente.setNome("Thiago Valverde de Souza");
        cliente.setLogin("valverde.thiago");
        cliente.setSenha("target@2019");
        cliente.setId(usuarioRepository.save(cliente).getId());
        return cliente;
    }

    public Funcionario createFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setLogin("administrador");
        funcionario.setMatricula("12345678");
        funcionario.setNome("Administrador do Sistema");
        funcionario.setSenha("1q2w3e");
        funcionario.setId(usuarioRepository.save(funcionario).getId());
        return funcionario;
    }
}
