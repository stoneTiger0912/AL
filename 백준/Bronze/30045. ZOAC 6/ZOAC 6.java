import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Exception {
    public static void main(String[] args) throws IOException {
        // 테스트케이스를 int로 받고, 각 문장을 String으로 받는다.
        // String str.contains("")를 사용한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < num; i++) {
            String str = br.readLine();

            if (str.contains("01") || str.contains("OI")){
                count++;
            }
        }

        sb.append(count);
        System.out.println(sb.toString());
    }
}