package br.com.targettrust.locadora.db;



import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public interface CarroRepository {
	
	void insertCarro(Carro carro);
	
	void updateCarro(Carro carro);
	
	List<Carro> listCarros();
	
	void delete(String placa);
	

}
