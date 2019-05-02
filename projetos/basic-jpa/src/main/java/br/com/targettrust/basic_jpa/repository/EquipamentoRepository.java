package br.com.targettrust.basic_jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.targettrust.basic_jpa.entity.Equipamento;

public class EquipamentoRepository {

	private EntityManager entityManager;

	public EquipamentoRepository() {
		super();
		this.entityManager = Persistence.createEntityManagerFactory("tracPU").createEntityManager();
	}

	public void save(Equipamento equipamento) {
		this.entityManager.persist(equipamento);
	}


	public void deleteByDescricao(String descricao) {
		String jpql = "delete Equipamento equipamento where equipamento.descricao = :descricao";
		Query query = this.entityManager.createQuery(jpql);
		this.entityManager.getTransaction().begin();
		query.setParameter("descricao", descricao);
		query.executeUpdate();
		this.entityManager.getTransaction().commit();
	}

}
