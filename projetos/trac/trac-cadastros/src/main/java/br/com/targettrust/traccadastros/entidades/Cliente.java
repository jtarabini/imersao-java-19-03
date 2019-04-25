package br.com.targettrust.traccadastros.entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="tb_cliente")
@AttributeOverrides({
	@AttributeOverride(name="login", column = @Column(name="cli_login")),
	@AttributeOverride(name="senha", column = @Column(name="cli_senha")),
	@AttributeOverride(name="nome", column = @Column(name="cli_nome")),
	@AttributeOverride(name="versao", column=@Column(name="cli_versao"))

})
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
	
	
}
