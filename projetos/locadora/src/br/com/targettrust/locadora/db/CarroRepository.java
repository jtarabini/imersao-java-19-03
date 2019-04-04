package br.com.targettrust.locadora.db;



import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public interface CarroRepository {
	
	void insert(Carro carro);
	
	void update(Carro carro);
	
	List<Carro> list();
	
	void delete(String placa);

	Carro findByPlaca(String placa);

	void delete(Integer id);
	

}
