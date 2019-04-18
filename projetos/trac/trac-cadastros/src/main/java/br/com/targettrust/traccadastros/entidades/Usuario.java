package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario extends Entidade {
	
	@Column(unique=true)
	private String login;
	@Column(length=10)
	private String senha;
	@Column(length=50)
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
