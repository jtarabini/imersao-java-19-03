package br.com.targettrust.traccadastros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.targettrust.traccadastros.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
