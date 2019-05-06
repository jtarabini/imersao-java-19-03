package br.com.targettrust.traccadastros.repositorios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {
	
	private static final String LOGIN_INVALIDO_MAXIMO_DEFAULT = "123456789012345678901";
	private static final String LOGIN_INVALIDO_MINIMO_DEFAULT = "123456789";
	private static final String LOGIN_DEFAULT = "administrador";
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Before
	@After
	public void setup() {
		usuarioRepository.deleteClienteByLogin(LOGIN_DEFAULT);
		usuarioRepository.deleteClienteByLogin(LOGIN_INVALIDO_MAXIMO_DEFAULT);
		usuarioRepository.deleteClienteByLogin(LOGIN_INVALIDO_MINIMO_DEFAULT);
	}
	
	
	@Test
	public void basicInsert() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_DEFAULT);
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		usuarioRepository.save(cliente);		
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testaEMailInvalido() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_DEFAULT);
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm_target");
		usuarioRepository.save(cliente);
	}
	
	@Test
	public void testaEmailValido() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_DEFAULT);
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm@target.com.br");
		usuarioRepository.save(cliente);
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testaSenhaInvalidaMinima() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_DEFAULT);
		cliente.setSenha("12345");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm@target.com.br");
		usuarioRepository.save(cliente);
	}

	@Test(expected=TransactionSystemException.class)
	public void testaSenhaInvalidaMaxima() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_DEFAULT);
		cliente.setSenha("12345678901234");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm@target.com.br");
		usuarioRepository.save(cliente);
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testaLoginInvalidoMinimo() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_INVALIDO_MINIMO_DEFAULT);
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm@target.com.br");
		usuarioRepository.save(cliente);
	}

	@Test(expected=TransactionSystemException.class)
	public void testaLoginInvalidoMaximo() {
		Cliente cliente = new Cliente();
		cliente.setLogin(LOGIN_INVALIDO_MAXIMO_DEFAULT);
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		cliente.setEmail("adm@target.com.br");
		usuarioRepository.save(cliente);
	}
}
