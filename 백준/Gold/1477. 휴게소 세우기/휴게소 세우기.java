import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int conv = Integer.parseInt(st.nextToken());
		int last = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		list.add(0);
		list.add(last);
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list);
		
		int left = 1;
		int right = last-1;
		
		int result = 0;
		while(left<=right) {
			int mid = (left + right) / 2;
			
			int cnt = check(mid);
			
			if(cnt > conv) {
				left = mid+1;
			}
			else {
				right = mid-1;
				
				result = mid;
			}
		}
		
		System.out.println(result);
		
	}

	private static int check(int mid) {
		int cnt = 0;
		
		for(int i=0; i<list.size()-1;i++) {
			int length = list.get(i+1) - list.get(i);
			
			cnt += length / mid;
			
			if(length % mid == 0) {
				cnt--;
			}
		}
		
		return cnt;
	}
}