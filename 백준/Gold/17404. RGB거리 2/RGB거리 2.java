import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[][] dp = new int[n+1][3];
		int[][] arr = new int[n+1][3];

		for(int i=1; i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());

			arr[i][0] = red;
			arr[i][1] = green;
			arr[i][2] = blue;

		}

		int[] minList = new int[3];
		Arrays.fill(minList, Integer.MAX_VALUE);

		for(int k=0; k<3;k++) {
			for(int c=0; c<3;c++) {
				if(k==c) {
					dp[1][c] = arr[1][c];
				}
				else dp[1][c] = 1001;
			}


			for(int i=2; i<=n;i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];

				if(i==n) {
					if(k==0) {
						minList[k] = Math.min(dp[n][1], dp[n][2]);

					}
					if(k==1) {
						minList[k] = Math.min(dp[n][0], dp[n][2]);

					}
					if(k==2) {
						minList[k] = Math.min(dp[n][0], dp[n][1]);

					}
				}
			}

		}
		
		System.out.println(Math.min(minList[0], Math.min(minList[1], minList[2])));


	}
}