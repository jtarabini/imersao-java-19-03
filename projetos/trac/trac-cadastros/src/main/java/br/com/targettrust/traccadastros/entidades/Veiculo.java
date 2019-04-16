package br.com.targettrust.traccadastros.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_veiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Veiculo extends Entidade {

	@Column(name="vcl_placa", length=8, unique=true)
	private String placa;
	@Column(name="vcl_ano", length=4)
	private Integer ano;
	@Column(name="vcl_cor", length=20)
	private String cor;
	@Column(name="vcl_marca", length=10)
	private String marca;
	@Column(name="vcl_modelo", length=20)
	private String modelo;

	@ManyToMany
	@JoinTable(name = "rl_veiculo_equipamento", 
		inverseJoinColumns = {@JoinColumn(name = "id_equipamento", referencedColumnName = "id") }, 
		joinColumns = {@JoinColumn(name = "id_veiculo", referencedColumnName = "id") } )
	private Set<Equipamento> equipamentos;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

}
