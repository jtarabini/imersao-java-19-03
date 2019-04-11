package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Equipamento extends Entidade {
	
	@Column
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
