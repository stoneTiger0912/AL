import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * @author SSAFY
 *11620kb	80ms
 */
public class Main {
	
	static int[][] graph;
	static long[][][] dp;
	
	static int n;
	
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	/**
	 * 누적합 구하기
	 * 각 부분에 대해 맞는 곳에 넣기
	 */
	static void check(int r, int c, int dir, long num) {
//		System.out.println(r+" "+c+" "+dir);
		
		int nr = 0;
		int nc = 0;
		
		switch (dir) {
		case 1:
			for(int d=0; d<3;d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc >= n||graph[nr][nc]==1) return;
			}
			nr = r + dr[dir];
			nc = c + dc[dir];
			dp[nr][nc][dir] += num;
			
			break;
		case 0:
		case 2:
			nr = r+dr[dir];	
			nc = c+dc[dir];	
//			System.out.println(nr+" "+nc);
			
			if(nr < 0 || nr >= n || nc < 0 || nc >= n||graph[nr][nc]==1) return;
			dp[nr][nc][dir] += num;
			break;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		
		//0은 가로 2은 세로 1는 대각선
		dp = new long[n][n][3];
		
		for(int r=0;r<n;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<n;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][1][0] = 1;
		for(int r=0; r<n;r++) {
			for(int c=0; c<n;c++) {
				if(r==0 && c==0) continue;
				
				for(int k=0; k<3;k++) {
					if(dp[r][c][k]==0) continue;
					
					for(int d=0; d<3;d++) {
						if(k==0 && d==2) continue;
						
						if(k==2 && d==0) continue;
						
						check(r, c, d, dp[r][c][k]);
					}
					
				}
			}
		}
		
//		print();
		long sum = dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2];
		System.out.println(sum);
		
	}
	
	static void print() {
		for(int k=0; k<3;k++) {
			for(int r=0; r<n;r++) {
				for(int c=0; c<n;c++) {
					System.out.print(dp[r][c][k]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println();
	}
}