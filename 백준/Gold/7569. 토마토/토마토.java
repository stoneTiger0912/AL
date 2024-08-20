import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * c x r x h
 * 위 아래 상 하 좌 우
 */
public class Main {
	
	static int R, C, H;
	static int[][][] graph;
	
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dr = {0, 0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 0, -1, 1};
	
	static Queue<Node> queue = new ArrayDeque<>();
	static boolean[][][] visited;
	
	static class Node {
		int h;
		int r;
		int c;
		
		Node(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [h=" + h + ", r=" + r + ", c=" + c + "]";
		}
		
	}
	
	static int BFS() {
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node node = queue.poll();
				int h = node.h;
				int r = node.r;
				int c = node.c;
				
				for(int d=0;d<6;d++) {
					int nh = h+dh[d];
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nh < 0 || nh >=H || nr < 0 || nr >=R || nc < 0 || nc >= C || graph[h][r][c] == -1 || visited[nh][nr][nc]) continue;
					
					if(graph[nh][nr][nc]==0) {
						graph[nh][nr][nc] = 1;
						visited[h][r][nc] = true;
						queue.offer(new Node(nh, nr, nc));
					}
					
					
				}
				
			}
			cnt++;
		}
		return cnt-1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new int[H][R][C];
		visited = new boolean[H][R][C];
		
		for(int h=0; h<H;h++) {
			for(int r=0; r<R;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<C;c++) {
					graph[h][r][c] = Integer.parseInt(st.nextToken());
					if(graph[h][r][c]==1) {
						queue.offer(new Node(h, r, c));
						visited[h][r][c] = true;
					}
				}
			}
		}
		
//		print();
		int result = BFS();
		boolean flag = false;
		
		A: for(int h=0; h<H;h++) {
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					if(graph[h][r][c]==0) {
						flag = true;
						break A;
					}
				}
			}
		}
		
		System.out.println(flag? -1:result);
		
	}
	
	static void print() {
		for(int h=0; h<H;h++) {
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					System.out.print(graph[h][r][c]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println();
	}
}