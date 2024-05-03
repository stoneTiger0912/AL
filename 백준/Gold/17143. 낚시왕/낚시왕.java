import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R, C, M;

	static class Shark implements Comparable<Shark> {
		int r;
		int c;
		int speed;
		int d;
		int size;


		public Shark() {
		}

		public Shark(int r, int c, int speed, int d, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.size, o.size)*-1;
		}

		public void move() {

			for(int t=0; t<speed;t++) {
				int nr = this.r + dr[d];
				int nc = this.c + dc[d];

				if(nr==0 || nr == R+1 || nc ==0 || nc == C+1) {
					this.d = 3 - this.d;
					nr = this.r + dr[d];
					nc = this.c + dc[d];
				}

				this.r = nr;
				this.c = nc;
			}
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", speed=" + speed + ", d=" + d + ", size=" + size + "]";
		}
		
		


	}

	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};

	static List<Shark> list;

	static void move() {

		Shark[][] sharks = new Shark[R+1][C+1];

		Collections.sort(list);
		int size = list.size();
		while(--size>=0) {
			Shark shark = list.remove(0);
			shark.move();

			if(sharks[shark.r][shark.c]==null) {
				sharks[shark.r][shark.c] = shark;
				list.add(shark);
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			//위 아래 오른쪽 왼쪽
			//위 왼쪽 오른쪽 아래
			if(d==1) d = 0;
			else if(d==2) d = 3;
			else if(d==3) d = 2;
			else if(d==4) d = 1;
			
			//상하인 경우
			if(d==0 || d==3) {
				speed %= ((R-1)*2);
			}
			else {
				speed %= ((C-1)*2);
			}

			Shark shark = new Shark(r, c, speed, d, size);
			list.add(shark);
		}

		int result = 0;

		for(int c=1; c<=C;c++) {
			int idx = -1;
			for(int i=0; i<list.size();i++) {
				//같은 줄에 있으면
				Shark shark = list.get(i);
				if(shark.c==c) {
					if(idx==-1) idx = i;
					else {
						if(list.get(idx).r < shark.r) continue;
						idx = i;
					}
				}
			}
			//제거
			if(idx!=-1) {
				Shark removeShark = list.remove(idx);
				result += removeShark.size;
			}
			//움직임
			move();
			
//			for(Shark s:list) {
//				System.out.println(s);
//			}
//			System.out.println(result);
		}
		
//		for(Shark s:list) {
//			System.out.println(s);
//		}
		
		System.out.println(result);
		
	}
}