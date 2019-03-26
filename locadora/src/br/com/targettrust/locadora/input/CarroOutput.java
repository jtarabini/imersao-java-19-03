<<<<<<< HEAD
package br.com.targettrust.locadora.input;

import java.util.List;

import br.com.targettrust.locadora.db.CarroRepository;
import br.com.targettrust.locadora.db.CarroRepositoryImpl;
import br.com.targettrust.locadora.entidades.Carro;

public class CarroOutput {
	
	public static void main(String[] args) {
		CarroRepository carroRepository = new CarroRepositoryImpl();
		List<Carro> carros = carroRepository.listCarros();
		System.out.println(carros.size());
	}

}
=======
package br.com.targettrust.locadora.input;

import java.util.List;

import br.com.targettrust.locadora.db.CarroRepository;
import br.com.targettrust.locadora.db.CarroRepositoryImpl;
import br.com.targettrust.locadora.entidades.Carro;

public class CarroOutput {
	
	public static void main(String[] args) {
		CarroRepository carroRepository = new CarroRepositoryImpl();
		List<Carro> carros = carroRepository.listCarros();
		System.out.println(carros.size());
	}

}
>>>>>>> 7a9648241bf71e41e55b651d8ac0159c83dd7437
