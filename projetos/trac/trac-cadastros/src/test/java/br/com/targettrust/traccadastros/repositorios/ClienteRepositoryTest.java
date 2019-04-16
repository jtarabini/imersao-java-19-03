package br.com.targettrust.traccadastros.repositorios;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.repositorio.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void basicInsert() {
		Cliente cliente = new Cliente();
		cliente.setLogin("administrador");
		cliente.setSenha("target");
		cliente.setNome("Administrador do Sistema");
		cliente.setEndereco("Rua São Francisco da Califórnia, 23");
		usuarioRepository.save(cliente);
		
	}

}
