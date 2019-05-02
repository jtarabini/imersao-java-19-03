package br.com.targettrust.plain_jpa.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.targettrust.plain_jpa.entidades.Equipamento;

public class EquipamentoRepository {
	
	private EntityManager entityManager;
	
	public EquipamentoRepository() {
		entityManager = Persistence
				.createEntityManagerFactory("tracPU")
				.createEntityManager();
	}
	
	public Equipamento save(Equipamento equipamento) {
		entityManager.getTransaction().begin();
		entityManager.persist(equipamento);
		entityManager.getTransaction().commit();
		return equipamento;
	}

	public void deleteByDescricao(String descricao) {
		String deleteQuery = "delete from Equipamento equipamento"
				+ " where equipamento.descricao = :descricao";
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery(deleteQuery);
		query.setParameter("descricao", descricao);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

}
