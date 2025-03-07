import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, X;
	static int[][] graph;
	static final int MAX = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		for(int i=1; i<=N;i++) {
			Arrays.fill(graph[i], MAX);
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[start][end] = cost;
		}
		
		for(int k=1; k<=N;k++) {
			for(int i=1; i<=N;i++) {
				for(int j=1; j<=N;j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}
		
		int[] dp = new int[N+1];
		int answer = Integer.MIN_VALUE;
		for(int i=1; i<=N;i++) {
            if(i==X) continue;
			answer = Math.max(answer, graph[i][X]+graph[X][i]);
		}
		System.out.println(answer);
		
	}
}
