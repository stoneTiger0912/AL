import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {

	static int[][] graph;
	static int[][] minGraph;

	static int n;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int result;

	static void move() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, graph[0][0]));
		minGraph[0][0] = graph[0][0];
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int r = node.r;
			int c = node.c;
			int num = node.num;
			if(r==n-1 && c==n-1) {
				result = Math.min(result, num);
			}
			
			
			for(int d=0; d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if(minGraph[nr][nc] <= num+graph[nr][nc]) continue;
				
				queue.offer(new Node(nr, nc, num+graph[nr][nc]));
				minGraph[nr][nc] = num+graph[nr][nc];
				
			}
			
		}
	}
	
	static class Node {
		int r;
		int c;
		int num;
		Node(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int turn =1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			result = Integer.MAX_VALUE;
			
			graph = new int[n][n];
			minGraph = new int[n][n];
			for(int r=0; r<n;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<n;c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[][] visited = new boolean[n][n];
			visited[0][0] = true;
			for(int r=0; r<n;r++) {
				Arrays.fill(minGraph[r], Integer.MAX_VALUE);
			}
			
			move();
			sb.append("Problem "+turn+": ").append(result+"\n");
			turn++;
			
		}
		
		System.out.println(sb);
	}

	static void print() {
		for(int r=0; r<n;r++) {
			for(int c=0; c<n;c++) {
				System.out.print(graph[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}