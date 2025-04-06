import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 모든 파이어볼은 d로 s만큼 이동
 * 1행 -> n행으로 이동, 열도 마찬가지
 * 0시부터 0-> 11시에 7방향
 * 이동이 끝난후 파이어볼 모두 합치기 -> 합친 것만
 * 질량은 W/N, 속력 -> 속력합 / 개수
 * 방향이 모두 짝수거나 홀수면 0 2 4 6
 * 질량 0이면 사라짐
 * 
 * K번 후 남은 파이어 볼의 합
 * 
 */
public class Main {
	
	static final int NOT_SAME = 100;
	
	static Node[][] graph;
	static int N, M, K;
	
	static class Node {
		int speed;
		int weight;
		int num;
		int d;
		
		Node(){
			this.speed = 0;
			this.weight = 0;
			this.num = 0;
			this.d = 0;
		}
		
		Node(int speed, int weight, int num, int d) {
			this.speed = speed;
			this.weight = weight;
			this.num = num;
			this.d = d;
		}
		
		void sum(FireBall f) {
			if(this.num==0) {
				this.num = 1;
				this.weight = f.weight;
				this.speed = f.speed;
				this.d = f.dir;
			}
			else {
				this.num+=1;
				this.weight += f.weight;
				this.speed += f.speed;
				
				if(this.d==NOT_SAME) return;
				
				if((d%2)!=(f.dir%2)) {
					this.d = NOT_SAME;
				}
			}
		}
		
	}
	
	static class FireBall {
		int r;
		int c;
		int weight;
		int speed;
		int dir;
		
		FireBall(int r, int c, int weight, int speed, int dir) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
		}

	}
	
	static Queue<FireBall> queue = new ArrayDeque<>();
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static void move() {
		
		while(!queue.isEmpty()) {
			FireBall fireBall = queue.poll();
			
			int r = fireBall.r;
			int c = fireBall.c;
			int speed = fireBall.speed % N;
			
			int dir = fireBall.dir;
			
			int nr = r + (dr[dir] * speed);
			int nc = c + (dc[dir] * speed);
			
			if(nr < 0) nr = N + nr;
			else if(nr >= N) nr %= N;
			
			if(nc < 0) nc = N + nc;
			else if(nc >= N) nc %= N;
			
			graph[nr][nc].sum(fireBall);
			
		}
		
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				if(graph[r][c].num==0) continue;
				
				else {
					Node n = graph[r][c];
					int weight = n.weight;
					int speed = n.speed;
					int dir = n.d;
					
					
					if(n.num==1) {
						queue.offer(new FireBall(r, c, weight, speed, dir));
					}
					else {
						
						weight = Math.floorDiv(weight, 5);
						
						if(weight==0) {
							graph[r][c] = new Node();
							continue;
						}
						
						speed = Math.floorDiv(speed, n.num);
						
						if(dir==NOT_SAME) {
							for(int i=1; i<8;i+=2) {
								queue.offer(new FireBall(r, c, weight, speed, i));
							}
						}
						else {
							for(int i=0; i<8;i+=2) {
								queue.offer(new FireBall(r, c, weight, speed, i));
							}
						}
						
					}
					
				}
				
				
				graph[r][c] = new Node();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new Node[N][N];
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				graph[r][c] = new Node();
			}
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			queue.offer(new FireBall(r, c, m, s, d));
			
		}
		
		for(int i=0; i<K;i++) {
			move();
		}
		
		int result = 0;
		while(!queue.isEmpty()) {
			FireBall f = queue.poll();
			
			result += f.weight;
		}
		
		System.out.println(result);
		
	}
}
