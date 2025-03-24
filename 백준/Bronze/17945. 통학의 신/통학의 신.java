import java.util.Scanner;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		int nRoot = (int) Math.sqrt(A*A - B);
		int nResult1 = (-1*A) - nRoot;
		int nResult2 = (-1*A) + nRoot;
		
		if(nResult1 == nResult2)
			System.out.println(nResult1);
		else
			System.out.println(nResult1 + " " + nResult2);
		
	}

}