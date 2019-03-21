package br.com.targettrust.locadora.entidades;

public class Veiculo extends Entidade{
	
	private String placa;
	private Integer ano;
	private String marca;
	private String modelo;
	private String cor;
	private Equipamento[] equipamentos;
	
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
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Equipamento[] getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(Equipamento[] equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	

}
