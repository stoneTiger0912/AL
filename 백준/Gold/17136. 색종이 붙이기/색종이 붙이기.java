import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	static final int N = 10;

	static List<Node> list = new ArrayList<>();

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int result = 26;
	
	static void print(int[][] map) {
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

//	static int num = 1;
	
	static void dfs(int num, int[] paperCnt, int[][] map, int cnt) {
//		System.out.println(Arrays.toString(paperCnt));

		if(cnt>=result) return;

//		print(map);
		if(num==list.size()) {
//			print(map);
//			System.out.println(cnt);
//			System.out.println(num);
//			System.out.println(Arrays.toString(paperCnt));
			result = Math.min(result, cnt);
			
			return;
		}

		Node node = list.get(num);
//		System.out.println(num);
		
		if(map[node.r][node.c]==0) {
			dfs(num+1, paperCnt, map, cnt);
		}
		
		for(int i=5; i>0;i--) {
			if(paperCnt[i]!=0) {
//				if(i==1) {
//					paperCnt[i]--;
//					map[node.r][node.c] = 0;
//					System.out.println(node.r+" "+node.c+" "+i);
////					print(map);
////					System.out.println(cnt);
//					dfs(num+1, paperCnt, map, cnt+1);
//					map[node.r][node.c] = 1;
//					paperCnt[i]++;
//				}
//				else {
					boolean flag = check(i, node.r, node.c, map);
					if(flag) {
						paperCnt[i]--;
						map = fillPaper(0, i, node.r, node.c, map);
//						print(map);
//						System.out.println(cnt);
//						System.out.println(node.r+" "+node.c+" "+i);
						dfs(num+1, paperCnt, map, cnt+1);
						map = fillPaper(1, i, node.r, node.c, map);
						paperCnt[i]++;
					}
//				}
				
				

			}
		}
	}

	static int[][] fillPaper(int isFill, int type, int curR, int curC, int[][] map) {
		for(int r=0; r<type;r++) {
			for(int c=0; c<type;c++) {
				map[r+curR][c+curC] = isFill;
			}
		}
		return map;
	}

	static boolean check(int type, int curR, int curC, int[][] map) {
		for(int r=0; r<type;r++) {
			for(int c=0; c<type;c++) {
				int nr  = r+curR;
				int nc  = c+curC;
				if(nr < 0 ||nr >=N || nc < 0 || nc >= N || map[nr][nc]==0) return false;
			}
		}

		return true;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] graph = new int[N][N];

		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());

				if(graph[r][c]==1) list.add(new Node(r, c));

			}
		}
		if(list.size()==0) {
			System.out.println(0);
		}


		else {
//			for(Node node:list) {
//				System.out.println(node.r+" "+node.c);
//			}

			int[] paperCnt = {0, 5, 5, 5, 5, 5};

			dfs(0, paperCnt, graph, 0);

			System.out.println(result==26 ? -1: result);
		}
	}
}