import java.util.Scanner;

public class SomaInteger {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String next = scanner.next();
		Integer soma = 0;
		do {
			soma = soma + Integer.valueOf(next);
			next = scanner.next();			
		} while(!next.equals("="));
		System.out.println(soma);
		scanner.close();
	}
}
