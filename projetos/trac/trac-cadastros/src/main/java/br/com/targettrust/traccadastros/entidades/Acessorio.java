package br.com.targettrust.traccadastros.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_acessorio")
public class Acessorio extends Entidade {
	
	@Column(name="acs_descricao")
	private String descricao;

	@ManyToMany
	@JoinTable(name = "rl_veiculo_acessorio", 
		inverseJoinColumns = {@JoinColumn(name = "id_acessorio", referencedColumnName = "id") }, 
		joinColumns = {@JoinColumn(name = "id_veiculo", referencedColumnName = "id") } )
	private Set<Veiculo> veiculos;

	@ManyToMany
	@JoinTable(name = "rl_modelo_acessorio", 
		inverseJoinColumns = {@JoinColumn(name = "id_acessorio", referencedColumnName = "id") }, 
		joinColumns = {@JoinColumn(name = "id_modelo", referencedColumnName = "id") } )
	private Set<Marca> modelos;

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

	public Set<Marca> getModelos() {
		return modelos;
	}

	public void setModelos(Set<Marca> modelos) {
		this.modelos = modelos;
	}
	
	

}
