package br.com.targettrust.locadora.db;

import java.util.List;

import br.com.targettrust.locadora.entidades.Equipamento;

public interface EquipamentoRepository {

	void insert(Equipamento equipamento);

	void update(Equipamento equipamento);

	void delete(Equipamento equipamento);

	List<Equipamento> list();

}
