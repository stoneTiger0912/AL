import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] list;
    static int[] sumList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //열차수
        N = Integer.parseInt(st.nextToken());
        list = new int[N+1];
        sumList = new int[N+1];

        //각 손님 수
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N;i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        //최대 M개 소형 기관차 3개
        M = Integer.parseInt(br.readLine());

        //누적해서 구함
        for(int i=1; i<=N;i++) {
//            if(i==0) sumList[i] = list[i];
            sumList[i] = sumList[i-1]+list[i];
        }

        int[][] dp = new int[4][N+1];
        for(int i=1;i<4;i++){
            for(int j=i * M;j<=N;j++)
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + sumList[j] - sumList[j-M]);
        }

        System.out.println(dp[3][N]);



    }
}