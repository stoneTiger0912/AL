import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * input c r
 * graph
 * 
 * 상하좌우 방향으로 토마토가 익은 거에 영향이 든다

	m과 n은 1000개
	
	토마토의 개수는 알지 못한다?
	
	1익 익은 토마토 0은 익지 않은 토마토
	
	BFS로 x,y의 간선의 수를 큐에 집어 넣고 4방향을 보면서 익지 않은 건 i+1로 바꾸면 됨
 */
public class Main {
	
	static int[][] graph;
	
	static int r, c;
	
	static class Node {
		int r;
		int c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> queue = new ArrayDeque<>();

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS() {
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			int curR = current.r;
			int curC = current.c;
			
			for(int i=0; i<4;i++) {
				int nr = current.r + dr[i];
				int nc = current.c + dc[i];
				
				if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
				
				if(graph[nr][nc]!=0) continue;
				graph[nr][nc] = graph[curR][curC] + 1;
				queue.offer(new Node(nr, nc));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		graph = new int[r][c];
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]==1) queue.offer(new Node(i, j));
			}
		}
		
		BFS();
		int max = -1;
		boolean flag = false;
		for(int i=0; i<r;i++) {
			for(int j=0; j<c;j++) {
				max = Math.max(max, graph[i][j]);
				//익지 않은 토마토 존재
				if(graph[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(max-1);
		
	}

}