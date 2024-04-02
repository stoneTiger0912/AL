import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;




public class Main {

	static int[][] graph;

	static int n;

	//상 좌 우 하
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};

	static int result;

	/**
	 * mingraph보다 작은경우만 큐에 넣어 탐색 
	 */

	static class Node {
		int r;
		int c;
		int size;
		int cnt = 0;

		Node(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}

		public int instance() {
			return Math.abs(r-babyShark.r) + Math.abs(c-babyShark.c);
		}

		public void eat(Move fish) {
			cnt++;
			graph[fish.r][fish.c] = 0;
			if(cnt==this.size) {
				this.size++;
				cnt = 0;
			}

			this.r = fish.r;
			this.c = fish.c;
			time += fish.time;
		}
	}

	static Node babyShark;

	static int time = 0;

	static void start() {

		while(true) {
			Move fish = find();

			if(fish==null) return;
			babyShark.eat(fish);
//			System.out.println(babyShark.r+" "+babyShark.c+" 상어");

//			System.out.println(time);
//			print();

		}

	}

	static class Move {
		int r;
		int c;
		int time;
		Move(int r, int c,int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		public int instance() {
			return Math.abs(r-babyShark.r) + Math.abs(c-babyShark.c);
		}
	}

	//	static PriorityQueue<Node> pq;

	//물고기 탐색 매번 pq는 초기화
	static Move find() {
		boolean[][] visited = new boolean[n][n];
		PriorityQueue<Move> pq = new PriorityQueue<>((o1, o2)-> 
		o1.time - o2.time == 0 ?
		o1.r - o2.r == 0 ? o1.c - o2.c : o1.r- o2.r : o1.time - o2.time
				);
		pq.offer(new Move(babyShark.r, babyShark.c, 0));
		visited[babyShark.r][babyShark.c] = true;

		int size = babyShark.size;

		while(!pq.isEmpty()) {
			Move move = pq.poll();
			int r = move.r;
			int c = move.c;
//			System.out.println(r+" "+c+" "+size);
			if(graph[r][c] < size && graph[r][c] != 0) {
				return new Move(r, c, move.time);
			}
			int time = move.time;
			for(int d=0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];

				if(nr < 0 || nr >= n ||nc < 0 || nc >= n || visited[nr][nc]) continue;

				if(graph[nr][nc] > size) continue;


				else if(graph[nr][nc] == size) {
					pq.offer(new Move(nr, nc, time+1));
					visited[nr][nc] = true;
				}

				else {
					if(graph[nr][nc]==0) {
						pq.offer(new Move(nr, nc, time+1));
						visited[nr][nc] = true;
					}

					else {
						pq.offer(new Move(nr, nc, time+1));
//						visited[nr][nc] = true;
					}
				}

			}

		}

		return null;
		//아래로 이동

	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		graph = new int[n][n];
		for(int r=0; r<n;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		A: for(int r=0; r<n;r++) {
			for(int c=0; c<n;c++) {
				if(graph[r][c]==9) {
					babyShark = new Node(r, c, 2);
					graph[r][c] = 0;
					break A;
				}

			}
		}
		start();
		System.out.println(time);
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