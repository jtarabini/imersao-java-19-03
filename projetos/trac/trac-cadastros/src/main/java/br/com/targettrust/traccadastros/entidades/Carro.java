package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {
	
	@Column(name="vcl_portas", length=1)
	private Integer portas;

	public Integer getPortas() {
		return portas;
	}

	public void setPortas(Integer portas) {
		this.portas = portas;
	}
}
