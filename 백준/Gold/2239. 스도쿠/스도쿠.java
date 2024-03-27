import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int N = 9;

	static int[][] graph = new int[N][N];

	static boolean[] search(int r, int c) {
		boolean[] flag = new boolean[N+1];
		for(int i=0; i<N;i++) {

			if(graph[r][i]==0) continue;
			else flag[graph[r][i]] = true;
		}

		for(int i=0; i<N;i++) {
			if(graph[i][c]==0) continue;
			else flag[graph[i][c]] = true;
		}


		int tmpR = (r / 3)*3;
		int tmpC = (c / 3)*3;

		for(int i=0; i<N/3;i++) {
			for(int j=0; j<N/3;j++) {
				if(graph[i+tmpR][j+tmpC]==0) continue;
				else flag[graph[i+tmpR][j+tmpC]] = true;
			}
		}
		return flag;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=0; i<N;i++) {
			String tmp = br.readLine();
			for(int j=0; j<N;j++) {
				graph[i][j] = tmp.charAt(j) - '0';
			}
		}

		back(0);

	}

	static void back(int idx) {
		
		if(idx==N*N) {
			print();
			System.exit(0);
		}

		int r = idx / N;
		int c = idx % N;
		
		if(graph[r][c]!=0) back(idx+1);
		else {
			
		boolean[] check = search(r, c);

			for(int i=1; i<=N;i++) {
				if(!check[i]) {
//					System.out.println(i+" "+r+" "+c);
					graph[r][c] = i;
					back(idx+1);

					graph[r][c] = 0;
				}
			}

		}

	}

	static void print() {
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}
	}
}