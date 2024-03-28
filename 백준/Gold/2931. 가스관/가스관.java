import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 *11640kb 80ms
 */
public class Main {

	static int R, C;

	static char[][] graph;

	static int startR, startC, endR, endC;

	static class Node {
		int r;
		int c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static boolean[][] visited;

	static int resultR, resultC;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startR, startC));
		visited[startR][startC] = true;
		queue.offer(new Node(endR, endC));
		visited[endR][endC] = true;

		while(!queue.isEmpty()) {
			Node current = queue.poll();
			int r = current.r;
			int c = current.c;

			boolean[] dir = new boolean[4];
			
			//각 조건 별로 탐색할 수 있는 방향이 정해짐
			switch(graph[r][c]) {
			case '|':
				dir[0] = true;
				dir[1] = true;
				break;
			case '-':
				dir[2] = true;
				dir[3] = true;
				break;
			case '+':
				for(int i=0; i<4;i++) {
					dir[i] = true;
				}
				break;
			case '1':
				dir[1] = true;
				dir[3] = true;
				break;
			case '2':
				dir[0] = true;
				dir[3] = true;
				break;
			case '3':
				dir[0] = true;
				dir[2] = true;
				break;
			case '4':
				dir[1] = true;
				dir[2] = true;
				break;
				
				
			//mz는 시작시점과 끝지점이므로 모든 방향 탐색해야함
			case 'Z':
			case 'M':
				for(int i=0; i<4;i++) {
					dir[i] = true;
				}
				break;
			}

			
			for(int d=0; d<4;d++) {
				if(!dir[d]) continue;

				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

				//1칸 이동했을 때 파이프가 존재하는지 체크 후 
				if(check(nr, nc, d)) {
					if(!visited[nr][nc]) {
						queue.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}

				//없으면
				else {
					//만약 처음이나 끝이면 계속 탐색
					if(graph[r][c] == 'M' || graph[r][c] == 'Z') continue;
					else {
						//만약 없으면 그때 좌표값 저장 후 끝내기
						resultR = nr;
						resultC = nc;

						return;
					}
				}

			}


		}
	}

	
	//체크하는 메서드
	static boolean check(int nr, int nc, int d) {
		switch (d) {
		case 0:
			if(graph[nr][nc] == '|' || graph[nr][nc] == '+' || graph[nr][nc] == '1' || graph[nr][nc] == '4' || graph[nr][nc] == 'Z' || graph[nr][nc] == 'M') {
				return true;
			}

			break;
		case 1:
			if(graph[nr][nc] == '|' || graph[nr][nc] == '+' || graph[nr][nc] == '2' || graph[nr][nc] == '3'  || graph[nr][nc] == 'Z' || graph[nr][nc] == 'M')  {

				return true;
			}

			break;
		case 2:
			if(graph[nr][nc] == '-' || graph[nr][nc] == '+' || graph[nr][nc] == '1' || graph[nr][nc] == '2' || graph[nr][nc] == 'Z'  || graph[nr][nc] == 'M') {

				return true;
			}

			break;
		case 3:
			if(graph[nr][nc] == '-' || graph[nr][nc] == '+' || graph[nr][nc] == '3' || graph[nr][nc] == '4'  || graph[nr][nc] == 'Z'  || graph[nr][nc] == 'M') {

				return true;
			}

			break;

		}

		return false;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];
		graph = new char[R][C];

		for(int r=0; r<R;r++) {
			String tmp = br.readLine();
			for(int c=0; c<C;c++) {
				graph[r][c] = tmp.charAt(c);
				if(graph[r][c]=='M') {
					startR = r;
					startC = c;
				}
				else if(graph[r][c]=='Z') {
					endR = r;
					endC = c;
				}
			}
		}

		BFS();

		int tmp = 0;
		A: for(int d=0; d<4;d++) {
			int nr = resultR + dr[d];
			int nc = resultC + dc[d];
			if(nr < 0 || nr >=R || nc < 0 || nc >= C) continue;
			boolean flag = check(nr, nc, d);
			
			//탐색하는 거 비트마스킹으로 체크
			if(flag) {
				if(graph[nr][nc] == 'M' || graph[nr][nc]=='Z') {
					for(int dir=0; dir<4;dir++) {
						int flagR = nr+dr[dir];
						int flagC = nc+dc[dir];
						
						if(check(flagR, flagC, dir)) continue A;
					}
					
					tmp += 1 << d;
				}
				else {
					tmp += 1 << d;
				}
			}
		}
		//		System.out.println(tmp);
		//상 하 좌 우
		//0 1 2 3
		//		for(int d=0; d<4;d++) {
		//			System.out.println(check[d]);
		//		}
		System.out.print((resultR+1)+" "+(resultC+1)+" ");
		switch (tmp) {
		case 3:
			System.out.println('|');
			break;
		case 5:
			System.out.println('3');
			break;
		case 9:
			System.out.println('2');
			break;
		case 6:
			System.out.println('4');
			break;
		case 10:
			System.out.println('1');
			break;
		case 12:
			System.out.println('-');
			break;
		default:
			System.out.println('+');
			break;
		}


	}
}