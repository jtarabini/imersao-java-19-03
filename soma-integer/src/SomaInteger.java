
public class SomaInteger {
	
	public static void main(String[] args) {
		if(args == null || args.length != 2) {
			System.out.println("Argumentos invalidos!");
		}
		else {
			Integer numA = Integer.valueOf(args[0]);
			Integer numB = Integer.valueOf(args[1]);
			System.out.print(numA+numB);
			
		}
	}

}
