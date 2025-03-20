import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static int N;
	static int[] arr, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int LIS = 0;
		
		for(int i=0; i<N;i++) {
			int idx = binarySearch(arr[i], 0, LIS, LIS+1);
			
			if(idx==-1) {
				dp[LIS] = arr[i];
				LIS++;
			}
			else {
				dp[idx] = arr[i];
			}
			
		}
		
		System.out.println(LIS);
		
	}
	
	static int binarySearch(int num, int start, int end, int size) {
		int res = 0;
		while(start <= end) {
			int mid = (start+end) / 2;
			if(dp[mid] < num) start = mid+1;
			else {
				end = mid-1;
				res = mid;
			}
		}
		
		if(start==size) return -1;
		else return res;
		
	}
}
