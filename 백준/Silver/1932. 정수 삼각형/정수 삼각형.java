import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n이 주어지면 크기가 n인 삼각형이 만들어짐
 * 이때 n의 크기는 밑변의 길이
 * 즉 arr[500][500]으로 입력값을 만들고
 * dp로 계산하면 될것 같음
 * dp의 경우 마지막의 하는 일을 반복해서 사용하면 됨
 * n-1까지 구한 것들에서 왼쪽 자식, 오른쪽 자식으로 값을 넣는데 그 중 큰값을 넣는 걸로
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][N];
		//한줄씩 개수가 1씩 늘어나므로 0이 아닌 1부터 시작하도록 설정
		for(int i=1; i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][N];
		for(int i=1; i<N+1;i++) {
			for(int j=0; j<i;j++) {
				dp[i][j] += arr[i][j];
				if(i!=N) {
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
					dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
				}
			}
		}
		
		int res = 0;
		
		for(int i=0; i<N;i++) {
			res = Math.max(res, dp[N][i]);
		}
		
		System.out.println(res);
		
	}
}