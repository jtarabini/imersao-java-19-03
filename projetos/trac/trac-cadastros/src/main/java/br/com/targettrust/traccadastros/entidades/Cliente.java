package br.com.targettrust.traccadastros.entidades;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="tb_cliente")
@AttributeOverrides({
	@AttributeOverride(name="login", column = @Column(name="cli_login")),
	@AttributeOverride(name="senha", column = @Column(name="cli_senha")),
	@AttributeOverride(name="nome", column = @Column(name="cli_nome")),
	@AttributeOverride(name="versao", column=@Column(name="cli_versao"))
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "sq_cliente", allocationSize = 1)
public class Cliente extends Usuario {
	
	@Column(name="cli_endereco")
	private String endereco;
	
	@Email
	@Column(name="cli_email")
	private String email;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
