import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 왼쪽으로 가는 경우 음수일 경우
 * 한번 간 지역은 가지 않는다
 * 1000x1000
 * 맨마지막에서 하는 행동
 * 1. 미지수 개수 확인 -> 왼쪽, 오른쪽, 위쪽 3개가 필요
 * 3방향에서 오는 것들을 체크하는 것이 중요
 * 오른쪽에서 오는 경우 
 * 2. 마지막 행동 -> r-1 c, r c-1 둘 중 하나가 된다
 * 
 */
public class Main {
	
	static int R, C;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R+1][C+1];
		int[][] dp = new int[R+1][C+1];
		int[][] tmp = new int[2][C+2];
		
		for(int r=1; r<R+1;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<C+1;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
//		print(graph);
		
		dp[1][1] = graph[1][1];
		
		for(int c=2;c<=C;c++) {
			dp[1][c] = graph[1][c] + dp[1][c-1];
		}
		
		for(int r=2; r<=R;r++) {
			tmp[0][0] = dp[r-1][1];

			//왼쪽에서 최대값
			for(int c=1; c<=C;c++) {
				tmp[0][c] = Math.max(tmp[0][c-1], dp[r-1][c]) + graph[r][c];
			}
			
			//으론쪽에서 최대값
			tmp[1][C+1] = dp[r-1][C];
			for(int c=C; c>=1;c--) {
				tmp[1][c] = Math.max(tmp[1][c+1], dp[r-1][c]) + graph[r][c];
			}
			
			for(int c=1; c<=C;c++) {
				dp[r][c] = Math.max(tmp[0][c], tmp[1][c]);
			}
		}
		
		System.out.println(dp[R][C]);
		
		
	}
	
	static void print(int[][] graph) {
		for(int[] r: graph) {
			for(int c: r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}