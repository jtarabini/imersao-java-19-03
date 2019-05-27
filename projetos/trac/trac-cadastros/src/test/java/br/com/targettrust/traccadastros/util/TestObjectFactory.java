package br.com.targettrust.traccadastros.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.targettrust.traccadastros.entidades.Carro;
import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;
import br.com.targettrust.traccadastros.entidades.Locacao;
import br.com.targettrust.traccadastros.entidades.Marca;
import br.com.targettrust.traccadastros.entidades.Modelo;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import br.com.targettrust.traccadastros.repositorio.MarcaRepository;
import br.com.targettrust.traccadastros.repositorio.ModeloRepository;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;

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


    public Reserva createReserva(Carro carro, LocalDate dataInicial, LocalDate dataFinal) {
        Reserva reserva = new Reserva();
        reserva.setDataInicial(dataInicial);
        reserva.setDataFinal(dataFinal);
        reserva.setVeiculo(carro);
        reserva.setFuncionario(createFuncionario());
        reserva.setCliente(createCliente());
        return this.reservaRepository.save(reserva);
    }

    public void createLocacao(Funcionario funcionario, Cliente cliente, Carro carro, LocalDate dataInicial,
                               LocalDate dataFinal) {
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(carro);
        locacao.setFuncionario(funcionario);
        locacao.setDataInicial(dataInicial);
        locacao.setDataFinal(dataFinal);
        locacao.setId(locacaoRepository.save(locacao).getId());
    }

    public Carro createCarro() {
    	Carro carro = (Carro) this.veiculoRepository.findByPlaca(PLACA_DEFAULT);
    	if(carro != null) {
    		return carro;
    	}
        carro = new Carro();
        carro.setModelo(this.createMarcaAndModelo(DEFAULT_MARCA, DEFAULT_MODELO, DEFAULT_ANO));
        carro.setAnoFabricacao(2019);
        carro.setPlaca(PLACA_DEFAULT);
        carro.setCor("Branca");
        carro.setPortas(4);
        carro.setId(veiculoRepository.save(carro).getId());
        return carro;
    }

    public Modelo createMarcaAndModelo(String marca, String modelo, int ano) {
    	Modelo modeloEntity = this.modeloRepository.findByNome(modelo);
    	if(modeloEntity != null) {
    		return modeloEntity;
    	}
        Marca marcaEntity = createMarca(marca);        
        modeloEntity = new Modelo();
        modeloEntity.setAno(ano);
        modeloEntity.setMarca(marcaEntity);
        modeloEntity.setNome(modelo);
        return this.modeloRepository.save(modeloEntity);
    }

	private Marca createMarca(String marca) {
		Marca marcaEntity = this.marcaRepository.findByNome(marca);
		if(marcaEntity != null) {
			return marcaEntity;
		}
		marcaEntity = new Marca();
        marcaEntity.setNome(marca);
        this.marcaRepository.save(marcaEntity);
		return marcaEntity;
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
