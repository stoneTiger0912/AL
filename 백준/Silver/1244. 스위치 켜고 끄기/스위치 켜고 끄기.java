import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int num, m;
	
	static int trans(int n) {
		if(n==1) return 0;
		else return 1;
	}
	
	static void male(int number) {
		int div = num / number;
		while(div!=0) {
			arr[div*number] = trans(arr[div*number]);
			div--;
		}
	}
	
	
	static void female(int number, int idx) {
		if(number-idx <=0 || number+idx>num) return;
		
		if(arr[number-idx]==arr[number+idx]) {
			arr[number-idx] = trans(arr[number-idx]);
			arr[number+idx] = trans(arr[number+idx]);
			female(number, idx+1);
		}
		else {
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[num+1];
		for(int i=1; i<=num;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		
		int s, n;
		for(int i=0; i< m;i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(s==1) male(n);
			else {
				arr[n] = trans(arr[n]);
				female(n, 1);
			}
		}

		//j~20+j
		//21~41
		//41~61
		//63
		arr = Arrays.copyOfRange(arr, 1, arr.length);
		//System.out.println(arr.length);
		
		int cnt = arr.length / 20;
		while(cnt!=0) {
			for(int i=0; i<20;i++) {
				System.out.print(arr[i]+" ");
			}
			cnt--;
			arr = Arrays.copyOfRange(arr, 20, arr.length);
			System.out.println();
		}
		
		for(int i=0; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		
	}

}