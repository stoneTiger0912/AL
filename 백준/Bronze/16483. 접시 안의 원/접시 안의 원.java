import java.io.*;

public class Main {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       double t = Double.parseDouble(br.readLine());
       System.out.printf("%.0f", (t / 2) * (t / 2));
   }
}