package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_cliente")
public class Cliente extends Usuario {
	
	@Column(name="cli_endereco")
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
