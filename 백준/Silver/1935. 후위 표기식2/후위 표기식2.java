import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		String input = br.readLine();
		
		Map<Integer, Double> map = new HashMap<>();
		Deque<Double> stack = new ArrayDeque<>();
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			double n = Integer.parseInt(st.nextToken());
			int key = 'A'+i;
			
			map.put(key, n);
		}
		
		for(int i=0; i<input.length();i++) {
			int k = input.charAt(i);
			
			if(k >= 'A' && k<='Z') {
				stack.push(map.get(k));
			}
			else {
				double right = stack.pop();
				double left = stack.pop();
				
				double tmp = 0;
				switch (k) {
				case '*':
					tmp = left * right;
					break;
				case '/':
					tmp = left / right;
//					System.out.println(tmp);
					break;
				case '+':
					tmp = left + right;
					break;
				case '-':
					tmp = left - right;
					break;
				}
//				tmp = Math.round((tmp * 100) / 100.0);
//				System.out.println(tmp);
				stack.push(tmp);
			}
			
		}
		
		System.out.printf("%.2f", stack.peek());
		
	}
}