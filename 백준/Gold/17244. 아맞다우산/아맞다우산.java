import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int N = (int) Math.pow(2,5);

	static int R, C;

	static char[][] graph;

	static boolean[][][] visited;

	static int objCnt = 0;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static class Node {
		int r;
		int c;
		int cnt;
		int check;
		int time;

		//체크는 비트마스킹으로 체그
		Node(int r, int c, int cnt, int check,int time) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.check = check;
			this.time = time;
		}
	}

	static int[] start, end;

	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(start[0], start[1], 0, 0, 0));
		visited[0][0][0] = true;

		int time = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node current = queue.poll();
				int r = current.r;
				int c = current.c;
				int cnt = current.cnt;
				int check = current.check;
				
				//System.out.println(r+" "+c+" "+cnt+" "+check);

				for(int d=0; d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];

					if(nr==end[0] && nc==end[1] && cnt==objCnt) {
						System.out.println(current.time+1);
						return;
					}

					if(nr < 0 ||nr >= R || nc < 0 ||nc >= C || graph[nr][nc] == '#') continue;

					if(graph[nr][nc]=='.' &&!visited[nr][nc][check]) {
						queue.offer(new Node(nr, nc, cnt, check, current.time+1));
						visited[nr][nc][check] = true;
					}
					else if(graph[nr][nc] >='0' && graph[nr][nc] <='9') {
						int num = graph[nr][nc] - '0';

						//체크는 지금까지 자나갔는지 확인
						if((check & (1<<num)) != 0) {
							//지금 위치에서 방문했으면 컨티뉴
							if(visited[nr][nc][check]) continue;
							//아니면 현재 위치에서 방문후 큐에 저장
							else {
								queue.offer(new Node(nr, nc, cnt, check, current.time+1));
								visited[nr][nc][check] = true;
							}
						}
						
						//안지난곳이면 
						else {
							queue.offer(new Node(nr, nc, cnt+1, check | (1<<num), current.time+1));
							visited[nr][nc][check|(1<<num)] = true;
						}
					}

				}

			}
			time++;
			//System.out.println(time+" time");
		}

	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new char[R][C];

		//11111 -> 100000
		visited = new boolean[R][C][N];

		int cnt = 0;

		for(int r=0; r<R;r++) {
			String str = br.readLine();
			for(int c=0; c<C;c++) {
				char tmp = str.charAt(c);
				if(tmp=='X') {
					graph[r][c] = Integer.toString(cnt).charAt(0);
					cnt++;
					continue;
				}
				else if(tmp=='S') {
					start = new int[] {r, c};
					graph[r][c] = '.';
				}
				else if(tmp=='E') {
					end = new int[] {r, c};					
					graph[r][c] = '.';
				}
				else {
					graph[r][c] = tmp;
				}
			}
		}
		objCnt = cnt;
		//print();
		BFS();
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