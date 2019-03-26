
public class HelloWorld {
	
	public static void main(String arg[]) {
		Carro corsa = new Carro(); 
		corsa.setPlaca("IST-7645");
		corsa.setMarca("Chevrolet");
		corsa.setModelo("Corsa Sedan");
		corsa.setCor("Prata");
		corsa.setAno(2010);
		
		Moto cg = new Moto(); 
		cg.setPlaca("HSP-4550");
		cg.setMarca("Honda");
		cg.setModelo("Honda CG Titan");
		cg.setCor("Vermelha");
		cg.setAno(2015);
		cg.setCilindrada(125);
		
		System.out.print(corsa.getMarca() + " - " 
				+ corsa.getModelo());
	}
	
	
	public static void main2(String args[]) {
		System.out.print("Hello Java World");
	}

}
