import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *119,640 kb
메모리
2,825 ms
 */
public class Main {

	static int N, M;

	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N+1][N+1];

		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph[s][l] = 1;
		}

		for(int k=1;k<=N;k++) {
			for(int r=1;r<=N;r++) {
				if(r==k) continue;
				for(int c=1;c<=N;c++) {
					if(graph[r][c]==1) continue;
					graph[r][c] = graph[r][k] & graph[k][c];
				}
			}
		}
		int result = 0;
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				graph[r][0] += graph[r][c];
				graph[0][c] += graph[r][c];
			}
		}
		for(int i=1; i<=N;i++) {
			if(graph[0][i]+graph[i][0] == N-1) result++;
		}
//		print();
		System.out.println(result);

	}
	
	static void print() {
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				System.out.print(graph[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}