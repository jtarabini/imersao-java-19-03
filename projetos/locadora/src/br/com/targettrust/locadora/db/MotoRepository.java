package br.com.targettrust.locadora.db;



import java.util.List;

import br.com.targettrust.locadora.entidades.Moto;

public interface MotoRepository {
	
	void insert(Moto moto);
	
	void update(Moto moto);
	
	List<Moto> list();
	
	void delete(String placa);

	Moto findByPlaca(String placa);

	void delete(Integer id);
	

}
