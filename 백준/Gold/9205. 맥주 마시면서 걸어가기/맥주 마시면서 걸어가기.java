
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	
	static int[][] graph;
	
	static int[][] input;
	
	static boolean[] visited;
	
	static StringBuilder sb;
	
	static class Node {
		int idx;
		int depth;
		
		Node(int idx, int depth) {
			this.idx = idx;
			this.depth = depth;
		}
	}
	
	
	static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			if(num==n+1) {
				sb.append("happy\n");
				return;
			}
			
			for(int i=0; i<n+2;i++) {
				if(num==i) continue;
				
				if(graph[num][i] <= 1000 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		sb.append("sad\n");
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			//처음처럼
			graph = new int[n+2][n+2];
			input = new int[n+2][n+2];
			visited = new boolean[n+2];
			
			
			for(int i=0; i<n+2;i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				input[i][0] = r;
				input[i][1] = c;
			}
			
			for(int r=0; r<n+2;r++) {
				for(int c=r+1; c<n+2;c++) {
					int len = Math.abs(input[r][0] - input[c][0]) + Math.abs(input[r][1] - input[c][1]);
					graph[r][c] = len;
					graph[c][r] = len;
				}
			}
//			print();
			BFS();
			
		}
		System.out.println(sb);
	}
	
	static void print() {
		for(int[] is:graph) {
			for(int i:is) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}

