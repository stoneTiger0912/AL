import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 오작교 주기 있음 = T%주기==0이어야 건널 수 있다. 건너는데 1초
	 * 두 번 연속으로 오작교를 건너지는 않는다 = 이미 오작교 위면 또 오작교 위로 가지 않는다.
	 * 
	 * visited[r][c][k]: k=0(새로 놓은 오작교가 없음) or 1(새로 오작교 놓고 건넘)
	 * 1. BFS
	 * 2. 현재 위치가 오작교 위가 아니고(1) 건널 수 있는 오작교가 있으면 건넌다.
	 * 3. 새로 오작교를 놓을 수 있는 경우 놓고 건넌다.
	 * 4. 직녀에게 도착하면 최소시간을 리턴한다.
	 * */
	
	static int N, M, result = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//input
		
		findCross();
		bfs();
		System.out.println(result);
	}//end of main
	
	private static void findCross() {
		
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c]==0) {	// 절벽일 때
					cnt=0;
					// 좌상/ 좌하/ 우상/ 우하
					if((r-1>=0 && map[r-1][c]==0) || (r+1<N && map[r+1][c]==0)) cnt++;
					if((c-1>=0 && map[r][c-1]==0) || (c+1<N && map[r][c+1]==0)) cnt++;
					if(cnt==2) map[r][c]=-1;
				}
			}
		}
	}
	
	private static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0,0,0,0});//r,c,k,t
		visited[0][0][0] = true;
		
		while(true) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int k = cur[2];
			int t = cur[3];
			int nr, nc, nt=t+1;
			
			if(r==N-1 && c==N-1) {
				result = t;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				
				if(nr<0||nc<0||nr>=N||nc>=N||visited[nr][nc][k]) continue;
				
				if(map[nr][nc]==1) { // 다음 위치가 땅일 때
					visited[nr][nc][k] = true;
					queue.offer(new int[] {nr, nc, k, nt});
				}
				if(map[nr][nc]>1) { // 다음 위치가 오작교 위일 때
					if(map[r][c]==1) {// 현재 위치가 땅이어야만 이동가능
						if(nt%map[nr][nc]==0) {// 이동가능하면
							queue.offer(new int[] {nr, nc, k, nt});
						}else {
							queue.offer(new int[] {r, c, k, nt});
						}
					}
				}
				if(map[nr][nc]==0 && k==0) { // 다음 위치가 땅일 때
					if(nt%M==0) queue.offer(new int[] {nr,nc,k+1,nt});
					else queue.offer(new int[] {r,c,k,nt});
				}
			}
		}
	}
}