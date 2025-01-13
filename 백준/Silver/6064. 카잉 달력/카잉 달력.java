import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 접근 과정
 * 1. M이 10이고 N이 12일때를 가정
 * N이 9일 경우 M은 순서대로 9 -> 1(9+12 % 10) -> 3(1+12 % 10)
 * 즉 N을 기준으로
 * visited[M+1]배열을 만들어서 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		A: for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[M+1];
			
			int day = y > M ? y % M == 0 ? M : y % M : y;
			int cnt = y;
			
			while(!visited[day]) {
				if(day==x) {
					sb.append(cnt).append("\n");
					continue A;
				}
				
				visited[day] = true;
				
				day += N;
				if(day > M) {
					day = day % M;
				}
				if(day % M == 0) {
					day = M;
				}
				
				cnt += N;
				
			}
			
			sb.append(-1).append("\n");
			
		}
		System.out.println(sb);
		
	}
}