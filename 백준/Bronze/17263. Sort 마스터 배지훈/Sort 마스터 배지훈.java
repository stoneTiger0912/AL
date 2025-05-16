import java.util.Scanner;
import java.util.Arrays;

public class Main {	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		int n=scanner.nextInt();
		int[] num=new int[n];
		
		for(int i=0;i<n;i++) {
			num[i]=scanner.nextInt();
		}
		
		Arrays.sort(num);
		
		System.out.println(num[n-1]);
    }
}