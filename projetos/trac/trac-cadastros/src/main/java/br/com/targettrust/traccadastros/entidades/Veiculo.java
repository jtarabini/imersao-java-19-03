package br.com.targettrust.traccadastros.entidades;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_veiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@AttributeOverrides({
	@AttributeOverride(name="versao", column=@Column(name="vcl_versao"))
})
public abstract class Veiculo extends Entidade {

	@Column(name="vcl_placa", length=8, unique=true)
	@Size(min=8, max=8)
	private String placa;
	@Column(name="vcl_ano_fabricacao", length=4)
	private Integer anoFabricacao;
	@Column(name="vcl_cor", length=20)
	private String cor;
	@ManyToOne
	@JoinColumn(name="id_modelo")
	private Modelo modelo;

	@ManyToMany(mappedBy = "veiculos")
	private Set<Acessorio> acessorios;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Set<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(Set<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

}
