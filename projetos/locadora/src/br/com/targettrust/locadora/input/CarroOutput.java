package br.com.targettrust.locadora.input;

import java.util.List;

import br.com.targettrust.locadora.db.CarroRepository;
import br.com.targettrust.locadora.db.CarroRepositoryImpl;
import br.com.targettrust.locadora.entidades.Carro;

public class CarroOutput {
	
	public static void main(String[] args) {
		CarroRepository carroRepository = new CarroRepositoryImpl();
		List<Carro> carros = carroRepository.list();
		System.out.println(carros.size());
	}

}
