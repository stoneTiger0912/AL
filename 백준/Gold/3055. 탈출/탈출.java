import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static final int DOCHI = 0, WATER = 1;
	
	static int R, C;
	
	static char[][] graph;
	
	static class Node {
		int r;
		int c;
		int type;
		
		Node(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
	}
	
	static int startR, startC, endR, endC;
	
	static List<Node> waterList = new ArrayList<>();
	
	static boolean[][][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};	
	
	static int BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		for(int i=0; i<waterList.size();i++) {
			Node node = waterList.get(i);
			queue.offer(node);
			visited[WATER][node.r][node.c] = true;
		}
		
		queue.offer(new Node(startR, startC, DOCHI));
		visited[DOCHI][startR][startC] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(--size >= 0) {
				Node node = queue.poll();
				
				
				int r = node.r;
				int c = node.c;

				if(node.type==DOCHI) {
					if(r==endR && c== endC) {
						return cnt;
					}
				}
				for(int d=0; d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr < 0 || nr >=R || nc < 0 || nc >=C || graph[nr][nc] == 'X') continue;
					
					if(node.type == WATER && !visited[WATER][nr][nc] && graph[nr][nc] != 'D') {
						visited[WATER][nr][nc] = true;
						queue.offer(new Node(nr, nc, WATER));
					}
					
					if(node.type == DOCHI && !visited[WATER][nr][nc] && !visited[DOCHI][nr][nc]){
						visited[DOCHI][nr][nc] = true;
						queue.offer(new Node(nr, nc, DOCHI));
					}
					
				}
				
			}
//			print(0);
//			print(1);
			cnt++;
		}
		return -1;
		
	}
	
	static void print(int j) {
		for(boolean[] is:visited[j]) {
			for(boolean i:is) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new char[R][C];
		visited = new boolean[2][R][C];
		
		for(int r=0; r<R;r++) {
			char[] tmp = br.readLine().toCharArray();
			graph[r] = tmp;
		}
		
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c] == '.' || graph[r][c] == 'X') continue;				
				//도치 시작점
				if(graph[r][c] == 'S') {
					startR = r;
					startC = c;
				}
				//비버 집
				else if(graph[r][c] == 'D') {
					endR = r;
					endC = c;
				} 
				//물
				else if(graph[r][c] == '*'){
					waterList.add(new Node(r, c, WATER));
				}
			}
		}
		int result = BFS();
		System.out.println(result==-1? "KAKTUS":result);
		
		
	}
}