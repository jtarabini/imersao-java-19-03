package br.com.targettrust.traccadastros.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_funcionario")
public class Funcionario extends Usuario{
	
	@Column(name="fnc_matricula", length=10)
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	

}
