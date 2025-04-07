import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] graph;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		for(int r=0; r<N;r++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int startR = N/2;
		int startC = N/2;
		int d = 0;
		int cnt = 1;
		A: while(true) {
			for(int i=0; i<2;i++) {
				for(int j=0; j<cnt;j++) {
					startR += dr[d];
					startC += dc[d];
					
					if(startR<0 || startC<0) break A;
					
//					System.out.println(startR+" "+startC+" "+d);
					
					if(graph[startR][startC]!=0) {
						move(startR, startC, d);
					}
					
//					print();
					
				}
				d = (d+1) % 4;
			}
			cnt++;
			
		}
		
		System.out.println(result);
		
	}
	
	static void print() {
		for(int[] r: graph) {
			for(int c: r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//왼쪽 기준으로 
	static int[][] offset = {
			{-1, 1},
			{1, 1}, //0.01
			{-2, 0},
			{2, 0}, //0.02
			{0, -2}, //0.05
			{-1, 0}, 
			{1, 0}, //0.07
			{-1, -1}, 
			{1, -1}, //0.1
			{0, -1} //a
	};
	
	static void move(int r, int c, int d) {
		
		int cnt = graph[r][c];
		
		graph[r][c] = 0;
		
		int[] sandList = new int[6];
		
		sandList[0] = (int)(cnt * 0.01);
		sandList[1] = (int)(cnt * 0.02);
		sandList[2] = (int)(cnt * 0.05);
		sandList[3] = (int)(cnt * 0.07);
		sandList[4] = (int)(cnt * 0.1);
		for(int i=0; i<5;i++) {
			
			if(i==2) {
				cnt -= sandList[i];
			}
			else cnt -= sandList[i] * 2;
		}
		
		sandList[5] = cnt;
		
//		System.out.println(Arrays.toString(sandList));
		
		switch(d) {
		//왼쪽으로
		case 0:
			for(int i=0; i<10;i++) {
				int nr = r + offset[i][0];
				int nc = c + offset[i][1];
				
				int idx = 0;
				
				switch (i) {
				case 0:
				case 1:
					idx = 0;
					break;
				case 2:
				case 3:
					idx = 1;
					break;
				case 4:
					idx = 2;
					break;
				case 5:
				case 6:
					idx = 3;
					break;
				case 7:
				case 8:
					idx = 4;
					break;
				default:
					idx = 5;
					break;
				}
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					result += sandList[idx];
					continue;
				}
				
//				System.out.println(nr+" "+nc);
//				System.out.println(idx);
				
				graph[nr][nc] += sandList[idx];
				
			}
			break;
		//아래쪽으로
		case 1:
			for(int i=0; i<10;i++) {
				int nr = r - offset[i][1];
				int nc = c + offset[i][0];
				
				int idx = 0;
				
				switch (i) {
				case 0:
				case 1:
					idx = 0;
					break;
				case 2:
				case 3:
					idx = 1;
					break;
				case 4:
					idx = 2;
					break;
				case 5:
				case 6:
					idx = 3;
					break;
				case 7:
				case 8:
					idx = 4;
					break;
				default:
					idx = 5;
					break;
				}
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					result += sandList[idx];
					continue;
				}
				
				graph[nr][nc] += sandList[idx];
				
			}
			break;
		//오른쪽으로
		case 2:
			for(int i=0; i<10;i++) {
				int nr = r + offset[i][0];
				int nc = c - offset[i][1];
				
				int idx = 0;
				
				switch (i) {
				case 0:
				case 1:
					idx = 0;
					break;
				case 2:
				case 3:
					idx = 1;
					break;
				case 4:
					idx = 2;
					break;
				case 5:
				case 6:
					idx = 3;
					break;
				case 7:
				case 8:
					idx = 4;
					break;
				default:
					idx = 5;
					break;
				}
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					result += sandList[idx];
					continue;
				}
				
				graph[nr][nc] += sandList[idx];
				
			}
			break;
		//위쪽으로
		case 3:
			for(int i=0; i<10;i++) {
				int nr = r + offset[i][1];
				int nc = c - offset[i][0];
				
				int idx = 0;
				
				switch (i) {
				case 0:
				case 1:
					idx = 0;
					break;
				case 2:
				case 3:
					idx = 1;
					break;
				case 4:
					idx = 2;
					break;
				case 5:
				case 6:
					idx = 3;
					break;
				case 7:
				case 8:
					idx = 4;
					break;
				default:
					idx = 5;
					break;
				}
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					result += sandList[idx];
					continue;
				}
				
				graph[nr][nc] += sandList[idx];
				
			}
			break;
		}
	}
}
