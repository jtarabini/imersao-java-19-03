package br.com.targettrust.locadora.input;

import java.util.Scanner;

import br.com.targettrust.locadora.db.CarroRepository;
import br.com.targettrust.locadora.db.CarroRepositoryImpl;
import br.com.targettrust.locadora.entidades.Carro;

public class CarroUpdateInput {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Carro carro = new Carro();
		System.out.println("Informe o id do carro a ser alterado");
		carro.setId(scanner.nextInt());
		System.out.println("Informe a marca");
		carro.setMarca(scanner.next());
		System.out.println("Informe o modelo");
		carro.setModelo(scanner.next());
		System.out.println("Informe o ano");
		carro.setAno(scanner.nextInt());
		System.out.println("Informe a placa");
		carro.setPlaca(scanner.next());
		System.out.println("Informe a cor");
		carro.setCor(scanner.next());
		System.out.println("Informe a quantidade de portas");
		carro.setPortas(scanner.nextInt());
		CarroRepository carroRepository = new CarroRepositoryImpl();
		carroRepository.update(carro);
	}

}
