import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 *K
 *r c
 *r*c개
 *
 *k번의 말 이동
 *3차원 배열 선언?
 */

public class Main {

	static int K, R, C;

	static int[][] graph;

	//0이 말 안쓰는 경우와 말 다쓴 경우
	//1이 말 쓰고 말이 있는 경우
	static boolean[][][] visited;

	static class Node {
		int r;
		int c;
		int cnt;
		Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	//4방 탐색
	static int[][] normalDir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	//8방 탐색
	static int[][] horseDir = { {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2} };


	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 0));
		visited[0][0][0] = true;

		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int j = 0; j<size;j++) {
				Node current = queue.poll();
				int r = current.r;
				int c = current.c;

//				System.out.println(cnt+" "+r+" "+c+" "+current.cnt);

				if(r == R-1 && c == C-1) {
					System.out.println(cnt);
					return;
				}
				//4방 탐색
				for(int i=0; i<4;i++) {
					int nr = r + normalDir[i][0];
					int nc = c + normalDir[i][1];

					if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 1) continue;
					
					if(!visited[current.cnt][nr][nc]) {
						visited[current.cnt][nr][nc] = true;
						queue.offer(new Node(nr, nc, current.cnt));
					}

					
//					if(current.cnt < K && visited[1][nr][nc] >= current.cnt) {
//						queue.offer(new Node(nr, nc, current.cnt));
//						visited[1][nr][nc] = current.cnt;
//					}
//					//말을 다쓴 경우
//					if(current.cnt == K && visited[0][nr][nc] ) {
//						queue.offer(new Node(nr, nc, current.cnt));
//						visited[0][nr][nc] = true;
//					}
//					if(current.cnt < K) visited[1][nr][nc] = true;
					//					if(current.cnt >= 0 && current.cnt < K && !visited[0][nr][nc]) visited[0][nr][nc] = true;
					//말이 한번도 되지 않은 경우
					//					else if(current.cnt == K && !visited[0][nr][nc]) {
					//						visited[0][nr][nc] = true;
					//					}
					


				}
				//8방 탐색
				if(current.cnt != K) {
					for(int i=0; i<8;i++) {
						int nr = r + horseDir[i][0];
						int nc = c + horseDir[i][1];

						if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 1) continue;

						if(!visited[current.cnt+1][nr][nc]) {
							visited[current.cnt+1][nr][nc] = true;
							queue.offer(new Node(nr, nc, current.cnt+1));
						}
						
					}
				}

			}
			cnt++;
		}

		System.out.println(-1);
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new int[R][C];
		visited = new boolean[K+1][R][C];

		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();
	}
}