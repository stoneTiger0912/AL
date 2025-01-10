import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C;
	static int[][] graph;
	static int startR, startC;
	
	static int[][] visited;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startR, startC));
		
		visited[startR][startC] = -1;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node n = queue.poll();
				
				int r = n.r;
				int c = n.c;
				
				for(int d=0;d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nr < 0 || nr >=R || nc < 0 || nc >=C || graph[nr][nc]!=1) continue;
					
					if(visited[nr][nc]!=0) continue;
					
					visited[nr][nc] = cnt+1;
					queue.offer(new Node(nr, nc));
					
				}
			}
			cnt++;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		visited = new int[R][C];
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				
				if(graph[r][c]==2) {
					startR = r;
					startC = c;
				}
				
			}
		}
		
		BFS();
		
		visited[startR][startC] = 0;
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]==1 && visited[r][c]==0) System.out.print(-1+" ");
				else System.out.print(visited[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		
	}
}