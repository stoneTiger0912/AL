import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int BFS(int s, int e) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(e);
		
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			while(--size>=0) {
				int n = queue.poll();
				
//				System.out.println(n);
				
				if(n==s) return cnt+1;
				
				if(n%10==1 && (s <= (n / 10))) {
					queue.offer(n/10);
				}
			
				if(n % 2 == 0 && (s <= (n / 2))) queue.offer(n/2);
			}
			
			cnt++;
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		int result = BFS(s, e);
		System.out.println(result);
	}
}