import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int END = 1_000_000_000;
	private static int result = Integer.MAX_VALUE;
	private static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] array = new int[N];
		
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(array[i], max);
		}
		
		binarySearch(array, max);
		System.out.println(result);
		
	}
	
	private static void binarySearch(int[] array, int max) {
		
		
		int start = max;
		int end = END;
		
		int mid = 0;
		
		while(start<=end) {
			mid = (start + end) / 2;
			boolean flag = check(array, mid);
			if(flag) {
//				System.out.println(start+":"+end+":"+mid);
				result = Math.min(result, mid);
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
	}
	
	private static boolean check(int[] array, int num) {
		
		int blueLayNum = 0;
		int tmp = 0;
		for(int i=0; i<N;i++) {
			if(tmp+array[i]>num) {
				blueLayNum++;
				tmp = array[i];
			}
			else {
				tmp += array[i];
			}
		}
		
		if(tmp !=0) blueLayNum++;
		
		if(blueLayNum<=M) {
//			System.out.println(blueLayNum+" "+num);
			return true;
		}
		
		return false;
	}
}