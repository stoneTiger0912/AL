import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> stack = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			arr[i] = num;
		}
		int idx = 0;
		for(int i=1; i<=N;i++) {
			stack.push(i);
			sb.append("+").append("\n");
			
			while(!stack.isEmpty() && stack.peek() == arr[idx]) {
				
				idx++;
				stack.pop();
				sb.append("-").append("\n");
				
			}
		}
		
		if(!stack.isEmpty()) System.out.println("NO");
		else System.out.println(sb);
		
	}
}