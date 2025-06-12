import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Exception {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true){
            String str = br.readLine();

            if (str.equals("***")){
                break;
            }else{
                StringBuffer buffer = new StringBuffer(str);
                String reverseStr = buffer.reverse().toString();

                sb.append(reverseStr).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}