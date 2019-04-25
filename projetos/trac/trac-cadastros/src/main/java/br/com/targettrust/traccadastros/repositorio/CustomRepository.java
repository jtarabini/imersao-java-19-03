package br.com.targettrust.traccadastros.repositorio;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.targettrust.traccadastros.entidades.Carro;

@Repository
public class CustomRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void saveCarro(Carro carro) {
		entityManager.persist(carro);
	}

}
