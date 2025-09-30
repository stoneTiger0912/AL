import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1개일 때 -> 1
 * WH
 * 2개일 때 -> 5
 * WH 
 * WWHH
 */
public class Main {
	
	static long[][] dp = new long[31][31];
	
	static long memo(int w, int h) {
		
		if(dp[w][h] != 0) return dp[w][h];
		
		if(w==0) return 1;
		
		if(w > 0) {
			dp[w][h] = memo(w-1, h+1);
		}
		
		if(h> 0) {
			dp[w][h] += memo(w, h-1);
		}
		
		return dp[w][h];
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		memo(30, 0);
		StringBuilder sb = new StringBuilder();
		//0은 1개를 꺼내는 경우, 1은 반개를 꺼내는 경우
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			if(n == 0) break;
			
			sb.append(dp[n][0]).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
