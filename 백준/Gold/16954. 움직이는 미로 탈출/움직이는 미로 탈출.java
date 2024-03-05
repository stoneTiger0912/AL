import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *8x8 체스판이 존재
 *.은 빈칸 #은 벽이다
 *가장 아래에는 벽이 사라짐
 *캐릭터가 먼저 움직이고 벽이 1칸씩 내려감
 *
 *욱재는 1초에 1칸 인접한 칸 혹은 대각선 방향으로 이동 혹은 가만히 있을 수 있음
 *1초동안 캐릭터가 먼저 이동 후 벽이 이동
 *
 *가장 왼쪽 아래에서 가장 오른쪽 위로 갈 수 있는지
 *
 *
 *고민
 *7, 0 -> 0, 7로 갈 수 있냐
 *
 *1. BFS
 *8방 탐색
 *64칸이어서 충분히 움직일듯
 *cnt를 하면서 
 *혹은 바로 위가 벽일 경우 못간다
 */
public class Main {
	static final int N = 8;

	static char[][] graph;

	static boolean[][][] visited;

	//12시부터
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0,-1};
	static int[] dc = {0, 0, 1, 1, 1, 0, -1, -1,-1};

	static class Node{
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static boolean BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(N-1, 0));
		visited[0][N-1][0] = true;

		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
//			for(Node node: queue) {
//				System.out.println(node.r+" "+node.c);
//			}
//			System.out.println();
			while(--size >= 0) {
				Node current = queue.poll();
				int r = current.r;
				int c = current.c;

				for(int d=0; d<9;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if(nr==0 && nc==N-1) return true;

					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[cnt+1][nr][nc]) continue;
					
					if(graph[nr][nc]=='#') continue;
					
					if(nr!=0 && graph[nr-1][nc]=='#') continue;
					
					queue.offer(new Node(nr, nc));
					visited[cnt+1][nr][nc] = true;
				}
			}
			cnt++;
			move();
			
//			print();
		}

		return false;
	}
	
	static void move() {
		for(int r=N-1; r>=0;r--) {
			for(int c=N-1; c>=0;c--) {
				if(graph[r][c]=='.') continue;
				else if(graph[r][c]=='#') {
					if(r==N-1) graph[r][c] = '.';
					else {
						graph[r][c] = '.';
						graph[r+1][c] = '#';
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		graph = new char[N][N];
		visited = new boolean[100][N][N];

		for(int r=0; r<N;r++) {
			graph[r] = br.readLine().toCharArray();
		}

//		print();

		if(BFS()) System.out.println(1);
		else System.out.println(0);
	}

	static void print() {
		for(char[] is:graph) {
			for(char i:is) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}