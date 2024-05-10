import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] graph;

	static void check(int r, int c) {
		for (int d1 = 1; d1 <= N - 2; d1++) {
			for (int d2 = 1; d2 <= N - 2; d2++) {

				if (r + d1 + d2 <= N && 1 <= c - d1 && c + d2 <= N) {
					search(r, c, d1, d2);
				}
			}
		}
	}

	static int result = Integer.MAX_VALUE;

	//인구수 계산
	//x는 왼쪽 y는 오른쪽
	static void search(int X, int Y, int d1, int d2) {
		int[][] tmpGraph = new int[N+1][N+1];

		int[] sum = new int[6];

		int sy = Y;
		int ey = Y;
		int cnt1 = d1;
		int cnt2 = d2;
		for (int x = X; x <= X + d1 + d2; x++) {
			for (int y = sy; y <= ey; y++) {
				sum[5] += graph[x][y];
				tmpGraph[x][y] = 5;
			}

			if (cnt1 > 0) {
				sy--;
				cnt1--;
			} 
			else sy++;

			if (cnt2 > 0) {
				ey++;
				cnt2--;
			} 
			else ey--;
		}

		for (int r = 1; r < X + d1; r++) {
			for (int c = 1; c <= Y; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[1] += graph[r][c];
					tmpGraph[r][c] = 1;
				}
			}
		}

		for (int r = 1; r <= X + d2; r++) {
			for (int c = Y + 1; c <= N; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[2] += graph[r][c];
					tmpGraph[r][c] = 2;
				}
			}
		}

		for (int r = X + d1; r <= N; r++) {
			for (int c = 1; c < Y - d1 + d2; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[3] += graph[r][c];
					tmpGraph[r][c] = 3;
				}
			}
		}

		for (int r = X + d2 + 1; r <= N; r++) {
			for (int c = Y - d1 + d2; c <= N; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[4] += graph[r][c];
					tmpGraph[r][c] = 4;
				}
			}
		}

		Arrays.sort(sum);
		result = Math.min(result, sum[5] - sum[1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];

		for(int r=1; r<=N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//		print(graph);

		for(int r=1; r<=N;r++) {
			for(int c=1; c<=N;c++) {
				check(r, c);
			}
		}
		System.out.println(result);
	}
	static void print(int[][] graph) {
		for(int[] r:graph) {
			for(int c:r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}