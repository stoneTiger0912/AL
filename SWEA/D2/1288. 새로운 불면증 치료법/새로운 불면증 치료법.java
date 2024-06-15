import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
//            System.out.println(n);

            int i = 1;
            int check = 0;
            int cnt = 1;
            while (true) {
                long tmp = n * i;

                while(tmp != 0) {
                    check |= (1 << (tmp % 10));
                    tmp /= 10;
                }

//                System.out.println(check);

                if(((check & Integer.parseInt("1111111111", 2) ) == Integer.parseInt("1111111111", 2))) {
//                    System.out.println("dd");
                    break;
                }
                cnt++;
                i++;
            }

//            System.out.println(cnt*n);
            sb.append("#").append(t).append(" ").append(cnt*n).append("\n");

        }
        System.out.println(sb);
    }
}