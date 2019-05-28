package br.com.targettrust.traccadastros.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_reserva")
@AttributeOverrides({
        @AttributeOverride(name = "versao", column = @Column(name = "rsv_versao"))
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "sq_reserva", allocationSize = 1)
public class Reserva extends Entidade {

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Column(name = "dt_inicial")
    @Future
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy HH:mm:ss",
            timezone = "BRT")
    private LocalDate dataInicial;

    @Column(name = "dt_final")
    @Future
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy HH:mm:ss",
            timezone = "BRT")
    private LocalDate dataFinal;

    @Column(name = "dt_cancelamento")
    @PastOrPresent
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy HH:mm:ss",
            timezone = "BRT")
    private LocalDate dataCancelamento;

    @ManyToMany
    @JoinTable(name = "rl_reserva_equipamento",
            inverseJoinColumns = {@JoinColumn(name = "id_equipamento", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "id_reserva", referencedColumnName = "id")})
    private Set<Equipamento> equipamentos;

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Set<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Set<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }


}
