import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static final int UP = 0, DOWN = 3, LEFT = 1, RIGHT = 2;
	
	static int R, C;
	static Shark[][] graph;
	//상 좌 우 하
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	
	static class Shark {
		int r;
		int c;
		int speed;
		int dir;
		int size;
		
		Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
		void move() {
			int cnt = this.speed;
			
			int nr = 0;
			int nc = 0;
			
			while(--cnt >= 0) {
				switch (this.dir) {
				case UP:
					
					nr = r + dr[this.dir];
					if(nr < 0) {
						this.dir = DOWN;
						nr = r + dr[this.dir];
					}
					
					this.r = nr;
					
					break;
				case DOWN:
					nr = r + dr[this.dir];
					if(nr >=R) {
						this.dir = UP;
						nr = r + dr[this.dir];
					}
					
					this.r = nr;
					break;
				case LEFT:
					nc = c + dc[this.dir];
					if(nc < 0) {
						this.dir = RIGHT;
						nc = c + dc[this.dir];
					}
					
					this.c = nc;
					break;
				case RIGHT:
					nc = c + dc[this.dir];
					if(nc >= C) {
						this.dir = LEFT;
						nc = c + dc[this.dir];
					}
					
					this.c = nc;
					break;
				}
			}
		}
	}
	
	static void move() {
		Queue<Shark> queue = new ArrayDeque<>();
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]!=null) {
					queue.offer(graph[r][c]);
					graph[r][c] = null;
				}
				
			}
		}
		
		while(!queue.isEmpty()) {
			Shark shark = queue.poll();
			
			shark.move();
			
			int r = shark.r;
			int c = shark.c;
			if(graph[r][c]==null) {
				graph[r][c] = shark;
			}
			else {
				if(graph[r][c].size < shark.size) {
					graph[r][c] = shark;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new Shark[R][C];
		
		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			switch (dir) {
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 3;
				break;
			case 3:
				dir = 2;
				break;
			case 4:
				dir = 1;
				break;
			}
			
			if(dir==UP || dir == DOWN) {
				speed %= (R-1) * 2;
			}
			else {
				speed %=(C-1) * 2;
			}
			
			Shark shark = new Shark(r, c, speed, dir, size);
			
			graph[r][c] = shark;
			
		}
		
		int result = 0;
		
		for(int c=0; c<C;c++) {
			for(int r=0; r<R;r++) {
				if(graph[r][c]!=null) {
					result += graph[r][c].size;
					graph[r][c] = null;
					break;
				}
			}
			
			//모든 상어 움직임
			move();
			
		}
		
		System.out.println(result);
		
	}
}
