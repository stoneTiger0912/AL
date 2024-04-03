import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 * 1. 미세먼지 확산 -> 큐에 넣고 한번에 확산
 * 	  미세먼지는 4방향으로 확산 -> R / 5를 하고 4방향으로 갈 수있는 개수를 구하고
 * 	 R - (r/5) * 방향
 * 	공기 청정기 위쪽은 반시계 -> r, c+1
 *  아래는 시계 방향
 *  방향으로 모두 1칸씩 이동
 *  
 *  -1에 공기 청정기 r, c, t
 *  7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
 */
public class Main {

	static int[][] graph;

	static int R, C, T;

	static class Node {
		int r;
		int c;
		int num;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Node(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static Node[] airList = new Node[2];

	static Queue<Node> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		graph = new int[R][C];

		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int airCnt = 0;
		A: for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]==-1) {
					airList[airCnt++] = new Node(r, c);
				}
				if(airCnt==2) break A; 
			}
		}

		for(int t=0; t<T;t++) {
			find();
			exp();
//			print();
			onAir();
//			print();
		}
		int result = 0;
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c] == 0 || graph[r][c]==-1) continue;
				result += graph[r][c];
			}
		}
		System.out.println(result);


	}

	static void onAir() {
		for(int i=0; i<2;i++) {
			Node air = airList[i];
			int d = 0;
			int nr = air.r+dr[d];
			int nc = air.c+dc[d];
			int tmpNum = graph[nr][nc];
			graph[nr][nc] = 0;
			if(i==0) {
				while(true) {
					nr += dr[d];
					nc += dc[d];

					if(nr < 0 || nr >=R || nc < 0 || nc >=C) {
						nr -= dr[d];
						nc -= dc[d];
						d = (d+1) % 4;
						continue;
					}
					if(graph[nr][nc]==-1) break;

					int tmp = graph[nr][nc];
					graph[nr][nc] = tmpNum;
					tmpNum = tmp;

				}
			}
			else {
				while(true) {
					nr += dr[d];
					nc += dc[d];

					if(nr < 0 || nr >=R || nc < 0 || nc >=C) {
						nr -= dr[d];
						nc -= dc[d];
						d = (d-1) == -1 ? 3:d-1;
						continue;
					}
					if(graph[nr][nc]==-1) break;

					int tmp = graph[nr][nc];
					graph[nr][nc] = tmpNum;
					tmpNum = tmp;

				}
			}

		}
	}
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};

	static void exp() {
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			int r = current.r;
			int c = current.c;

			int div = current.num / 5;
			int cnt = 0;
			for(int d=0; d<4;d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==-1) continue;
				graph[nr][nc] += div;
				cnt++;

			}

			graph[r][c] -= div * cnt;

		}
	}

	static void find() {
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]==0 || graph[r][c]==-1) continue;
				queue.offer(new Node(r, c, graph[r][c]));
			}
		}
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