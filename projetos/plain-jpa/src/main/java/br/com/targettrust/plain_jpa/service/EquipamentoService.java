package br.com.targettrust.plain_jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.targettrust.plain_jpa.entidades.Equipamento;
import br.com.targettrust.plain_jpa.repositorios.EquipamentoRepository;

public class EquipamentoService {
	
	private EquipamentoRepository equipamentoRepository;
	private EntityManager entityManager;
	
	public EquipamentoService() {
		entityManager = Persistence
				.createEntityManagerFactory("tracPU")
				.createEntityManager();
		this.equipamentoRepository = 
				new EquipamentoRepository(entityManager);
	}
	
	public void saveList(List<Equipamento> equipamentos) {
		entityManager.getTransaction().begin();	
		try {
			for(Equipamento equipamento : equipamentos) {
				equipamentoRepository.save(equipamento);
			}
			entityManager.getTransaction().commit();
		}
		catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

}
