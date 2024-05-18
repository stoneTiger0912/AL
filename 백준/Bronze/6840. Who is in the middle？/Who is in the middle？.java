import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number1 = sc.nextInt();
        int number2 = sc.nextInt();
        int number3 = sc.nextInt();

        ArrayList<Integer> al = new ArrayList<>();
        al.add(number1);
        al.add(number2);
        al.add(number3);
        //(1)
        Collections.sort(al);
		//(2)
        System.out.println(al.get(1));
        //(3)
    }
}