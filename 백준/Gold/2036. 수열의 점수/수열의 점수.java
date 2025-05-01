import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int zeroCnt = 0;

        PriorityQueue<Integer> plus = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1, o2) * -1
        );
        PriorityQueue<Integer> minus = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1, o2) * -1
        );

        for(int i=0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num==0) zeroCnt++;
            else if(num > 0) plus.offer(num);
            else minus.offer(num*-1);
        }

        long result = 0;

        while(!minus.isEmpty()) {
            if(minus.size()==1) {
                if(zeroCnt!=0) {
                    break;
                }
                else {
                    result -= minus.poll();
                }
            }
            else {
                long n1 = minus.poll();
                long n2 = minus.poll();

                result += n1*n2;
            }
        }

        while(!plus.isEmpty()) {
            if(plus.size()==1) {
                result += plus.poll();
            }
            else {
                long n1 = plus.poll();
                long n2 = plus.poll();

                result += Math.max(n1*n2, n1+n2);
            }
        }

        System.out.println(result);
    }
}
