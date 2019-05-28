package br.com.targettrust.traccadastros.entidades;

import javax.persistence.*;

@Entity
@Table(name="tb_funcionario")
@AttributeOverrides({
	@AttributeOverride(name="login", column = @Column(name="fnc_login")),
	@AttributeOverride(name="senha", column = @Column(name="fnc_senha")),
	@AttributeOverride(name="nome", column = @Column(name="fnc_nome")),
	@AttributeOverride(name="versao", column=@Column(name="fnc_versao"))
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "sq_funcionario", allocationSize = 1)
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
