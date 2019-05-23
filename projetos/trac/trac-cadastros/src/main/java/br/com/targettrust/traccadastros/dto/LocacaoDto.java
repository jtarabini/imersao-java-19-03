package br.com.targettrust.traccadastros.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.targettrust.traccadastros.entidades.Cliente;
import br.com.targettrust.traccadastros.entidades.Funcionario;

public class LocacaoDto {

	@NotNull
	private Long idVeiculo;
	@NotNull
	private Cliente cliente;
	@NotNull
	private Funcionario funcionario;
	@FutureOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Date dataInicial;
	@Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Date dataFinal;
	private Double valor;
	
	public Long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	

}
