import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = 1500;

        for(int i=0; i<4;i++) {
            n -= sc.nextInt();
        }

        if(n>=0) System.out.println("Yes");
        else System.out.println("No");

    }
}