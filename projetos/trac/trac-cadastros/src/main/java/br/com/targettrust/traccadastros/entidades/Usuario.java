package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Usuario extends Entidade {

	@Column(unique = true)
	@Size(min = 10, max = 20)
	private String login;

	@Column(length = 10)
	@Size(min = 6, max = 12)
	private String senha;

	@Column(length = 50)
	@Size(min = 10, max = 120)
	private String nome;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
