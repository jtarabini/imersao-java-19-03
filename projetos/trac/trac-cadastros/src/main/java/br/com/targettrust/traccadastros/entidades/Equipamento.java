package br.com.targettrust.traccadastros.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity(name="tb_equipamento")
public class Equipamento extends Entidade {
	
	@Column(name="eqp_descricao", unique=true, length=20)
	private String descricao;
	
	@ManyToMany(mappedBy="equipamentos")
	private Set<Veiculo> veiculos;
	
	@Version
	@Column(name="eqp_versao")
	private Integer versao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<Veiculo> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
}
