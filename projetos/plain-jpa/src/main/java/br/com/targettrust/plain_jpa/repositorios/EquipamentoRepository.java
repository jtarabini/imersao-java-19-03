package br.com.targettrust.plain_jpa.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.targettrust.plain_jpa.entidades.Equipamento;

public class EquipamentoRepository {
	
	private EntityManager entityManager;
	
	public EquipamentoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Equipamento save(Equipamento equipamento) {
		entityManager.persist(equipamento);
		return equipamento;
	}

	public void deleteByDescricao(String descricao) {
		String deleteQuery = "delete from Equipamento equipamento"
				+ " where equipamento.descricao = :descricao";
		Query query = entityManager.createQuery(deleteQuery);
		query.setParameter("descricao", descricao);
		query.executeUpdate();
	}

	public Equipamento findByDescricao(String descricao) {
		String jpql = "from Equipamento e where e.descricao= :descricao";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("descricao", descricao);
		return (Equipamento) query.getSingleResult();
	}

}
