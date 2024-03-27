import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] graph;
	static boolean[][][] visited;

	static int R, C;

	static int startR, startC;

	static class Node {
		int r;
		int c;
		int check;
		Node(int r, int c, int check) {
			this.r = r;
			this.c = c;
			this.check = check;
		}
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int result = 0;

	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startR, startC, 0));
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node current = queue.poll();
				int r = current.r;
				int c = current.c;
				
				
				
				int check = current.check;
				for(int d=0; d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					//밖으로 나가는 경우
					if(nr < 0 || nr >=R || nc < 0 || nc>= C||graph[nr][nc]=='#') continue;

					//방문 하지 않은 빈 벽일 경우 큐에 넣기
					if(graph[nr][nc]=='.') {
						if(!visited[check][nr][nc]) {
							queue.offer(new Node(nr, nc, check));
							visited[check][nr][nc] = true;
						}
					}
					else if(graph[nr][nc]=='1') {
						result = cnt+1;
						return;
					}
					else {
						//열쇠 인경우
						if(graph[nr][nc]>='a' && graph[nr][nc] <='f') {
							int flag = 1<< (graph[nr][nc] - 'a');
							flag |= check;
							if(!visited[flag][nr][nc]) {
								queue.offer(new Node(nr, nc, flag));
								visited[flag][nr][nc] = true;
							}
							
						}
						
						//문인 경우
						if(graph[nr][nc]>='A' && graph[nr][nc] <='F') {
							int flag = 1<< (graph[nr][nc] - 'A');
							if((check & flag) != 0 && !visited[check][nr][nc] ) {
								queue.offer(new Node(nr, nc, check));
								visited[check][nr][nc] = true;
							}
						}
						
					}



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

		graph = new char[R][C];
		visited = new boolean[64][R][C];

		for(int r=0; r<R;r++) {
			String tmp = br.readLine();
			for(int c=0; c<C;c++) {
				graph[r][c] = tmp.charAt(c);
			}
		}


		A: for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]=='0') {
					graph[r][c] = '.';
					startR = r;
					startC = c;
					break A;
				}
			}
		}

		BFS();
		
		System.out.println(result==0 ? -1: result);

	}

	static void print() {
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				System.out.print(graph[r][c]+" ");
			}
			System.out.println();
		}
	}
}