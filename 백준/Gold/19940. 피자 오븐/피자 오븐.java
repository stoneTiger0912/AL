import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 60 -> 1 0 0 0 0
 * 50 -> 1 0 1 0 0
 * 40 -> 1 0 2 0 0
 * 30 -> 0 3 0 0 0
 * 20 -> 0 2 0 0 0
 * 10 -> 0 1 0 0 0
 * 6 ~ 9 -> 0 1 0 0 N
 * 1 ~ 5 -> 0 0 0 0 N
 * 
 * 이므로 일단 일의 자리가 5를 넘으면 십의 자리를 사용
 * 십의 자리가 30이 넘어가면 60을 사용
 * 
 * 55 -> 60 - 5
 * 
 */
public class Main {
	
	static int[][] dp = new int[61][5];
	
	static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		boolean[] visited = new boolean[61];
		
		queue.offer(0);
		
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int N = queue.poll();
			for(int i=4;i>=0;i--) {
				int tmp = N;
				int[] tmpList = new int[5];
				System.arraycopy(dp[N], 0, tmpList, 0, 5);
				switch(i) {
					case 4:
						tmp -= 1;
						break;
					case 3:
						tmp += 1;
						break;
					case 2:
						tmp -= 10;
						break;
					case 1:
						tmp += 10;
						break;
					case 0:
						tmp += 60;
						break;
				}
				if(tmp < 0 || tmp > 60 || visited[tmp]) continue;
				visited[tmp] = true;
				tmpList[i] += 1;
				dp[tmp] = tmpList;
				queue.offer(tmp);
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		BFS();
		
//		for(int i=1; i<=60;i++) {
//			for(int j=0; j<5;j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			
			int addH = time / 60;
			
			time = time % 60;
			
			int[] result = new int[5];
			
			System.arraycopy(dp[time], 0, result, 0, 5);
			
			result[0] += addH;
		
		
			for(int i=0; i<5;i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb);
		 
	}
}