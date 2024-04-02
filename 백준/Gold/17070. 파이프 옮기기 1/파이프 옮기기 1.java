import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] graph;
	
	static int[][][] dp;
	
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	static void add(int r, int c, int d, int sum) {
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return;
		
		if(d!= 1) {
			if(graph[nr][nc]==1) return;
			
		}
		else {
			if(graph[nr][nc] == 1 || graph[nr-1][nc] == 1 || graph[nr][nc-1] == 1 ) return;
		}
		
		dp[nr][nc][d] += sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		//0은 오른쪽, 1은 대각 아래, 2는 아래
		dp = new int[N][N][3];
		
		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1][0] = 1;
		
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				
				if(r==N-1 && c== N-1) {
					int sum = 0;
					for(int i=0; i<3;i++) {
						sum += dp[r][c][i];
					}
					
					System.out.println(sum);
					return;
				}
				for(int i=0; i<3;i++) {
					if(dp[r][c][i] == 0) continue;
					
					switch(i) {
					case 0:
						//0과 1
						add(r, c, 0, dp[r][c][i]);
						add(r, c, 1, dp[r][c][i]);
						break;
					case 1:
						add(r, c, 0, dp[r][c][i]);
						add(r, c, 1, dp[r][c][i]);
						add(r, c, 2, dp[r][c][i]);
						break;
					case 2:
						add(r, c, 1, dp[r][c][i]);
						add(r, c, 2, dp[r][c][i]);
						break;
					}
				}
			}
		}
		
		
		
	}
}
