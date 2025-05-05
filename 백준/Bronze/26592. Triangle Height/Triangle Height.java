import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			double a = sc.nextDouble();
			double b = sc.nextDouble();
			double h = (2 * a) / b;
			System.out.println("The height of the triangle is " + String.format("%.2f", h) + " units");
		}
		sc.close();
	}
}