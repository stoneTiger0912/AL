import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;
	static int[] max, min;
	
	static void getMax() {
		for(int i=0; i<N;i++) {
			int idx = 0;
			
			while(dp[idx]!=0) {
				if(dp[idx] < arr[i]) idx++;
				else break;
			}
			dp[idx] = arr[i];
			max[i] = idx;
		}
	}
	
	static void getMin() {
		
		for(int i=N-1; i>=0;i--) {
			int idx = 0;
			
			while(dp[idx]!=0) {
				if(dp[idx] < arr[i]) idx++;
				else break;
			}
			dp[idx] = arr[i];
			min[i] = idx;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//0번째는 수, 1은 입력의 인덱
		dp = new int[N];
		max = new int[N];
		min = new int[N];
		
		getMax();
		dp = new int[N];
		getMin();
		
		int result = 0;
		for(int i=0; i<N;i++) {
			result = Math.max(result, min[i]+max[i]+1);
		}
		
//		System.out.println(Arrays.toString(max));
//		System.out.println(Arrays.toString(min));
		System.out.println(result);
		
	}
}
