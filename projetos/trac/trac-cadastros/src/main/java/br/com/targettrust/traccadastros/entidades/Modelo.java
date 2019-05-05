package br.com.targettrust.traccadastros.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_modelo")
public class Modelo extends Entidade{
	
	@Column(name="mdl_nome")
	private String nome;	
	@Column(name="mdl_versao")
	private String versao;
	@Column(name="mdl_ano")
	private Integer ano;
	
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;

	@ManyToMany(mappedBy = "modelos")
	private Set<Acessorio> acessorios;
	
	

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Set<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(Set<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
	
	

}
