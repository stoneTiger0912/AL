import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] array = new int[N+1][2];
		
		for(int i=1; i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			array[i][0] = w;
			array[i][1] = v;
		}
		
		//N번째를 넣을 경우 M+1이 최대인 가방을 채우는 경우
		int[][] dp = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1; j<=M;j++) {
				if(array[i][0] > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-array[i][0]] + array[i][1]);
				}
			}
		}
		
		System.out.println(dp[N][M]);
		
	}
}