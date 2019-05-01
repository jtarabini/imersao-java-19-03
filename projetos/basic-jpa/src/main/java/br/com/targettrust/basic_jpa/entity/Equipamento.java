package br.com.targettrust.basic_jpa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_equipamento")
@AttributeOverrides({
	@AttributeOverride(name="versao", column=@Column(name="eqp_versao"))
})
public class Equipamento extends Entidade {
	
	@Column(name="eqp_descricao", unique=true, length=20)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
