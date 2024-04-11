import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 */
public class Main {
	
	static int N;
	
	static boolean[][] graph;
	
	static int[] people;
	
	static int result = Integer.MAX_VALUE;
	
	static boolean[] selected;
	
	//1-3-4
	static void check() {
		
		int Aidx = 0;
		int Bidx = 0;

		int Acnt = 0;
		int Bcnt = 0;
		for(int i=1; i<=N;i++) {
//			System.out.print(selected[i]+" ");
			if(selected[i]) {
				Aidx = i;
				Acnt += people[i];
			}
			else {
				Bidx = i;
				Bcnt += people[i];
			}
		}
//		System.out.println(Acnt);
//		System.out.println(Bcnt);

		
		boolean[] visited = new boolean[N+1];
		boolean flag = true;
		DFS(Aidx, flag, visited);
		
		//visited = new boolean[N+1];
		flag = false;
		DFS(Bidx, flag, visited);
//		System.out.println(Aidx);
//		System.out.println(Bidx);
		
		
		for(int i=1; i<=N;i++) {
			if(!visited[i]) {
				return;
			}
		}
		
//		System.out.println();
		
		result = Math.min(result, Math.abs(Acnt - Bcnt));
		
	}
	
	
	static void DFS(int start, boolean flag, boolean[] visited) {
		visited[start] = true;
		for(int i=1; i<=N;i++) {
			if(!graph[start][i]) continue;
			if((selected[i] == flag) && !visited[i]) {
				//visited[i] = true;
				DFS(i, flag, visited);
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N+1][N+1];
		people = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N;i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j=0;j<m;j++) {
				int node = Integer.parseInt(st.nextToken());
				graph[i][node] = true;
			}
		}
		
		
		
		//최대 1024
		for(int i=1; i<(int)Math.pow(2, N);i++) {
			selected = new boolean[N+1];
			for(int j=0; j<N;j++) {
				
				if((i & (1 << j)) == (1 << j)) {
					selected[j+1] = true;
				}
			}

			check();
			
			
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
}