<<<<<<< HEAD
package br.com.targettrust.locadora.db;



import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public interface CarroRepository {
	
	void insertCarro(Carro carro);
	
	void updateCarro(Carro carro);
	
	List<Carro> listCarros();
	
	void delete(String placa);
	

}
=======
package br.com.targettrust.locadora.db;



import java.util.List;

import br.com.targettrust.locadora.entidades.Carro;

public interface CarroRepository {
	
	void insertCarro(Carro carro);
	
	void updateCarro(Carro carro);
	
	List<Carro> listCarros();
	
	void delete(String placa);
	

}
>>>>>>> 7a9648241bf71e41e55b651d8ac0159c83dd7437
