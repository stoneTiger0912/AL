import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String text = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String check = st.nextToken();
		
		int textLen = text.length();
		int checkLen = check.length();
		
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<textLen;i++) {
			int cnt = 0;
			stack.push(text.charAt(i));
			
			if(stack.size()>=checkLen) {
				
				for(int j=0; j<checkLen;j++) {
					if(stack.get(stack.size()-checkLen + j) == check.charAt(j)) {
						cnt++;
					}
					
					if(cnt==checkLen) {
						for(int k=0; k<checkLen;k++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {
			StringBuilder sb = new StringBuilder();
			for(char ch: stack) {
				sb.append(ch);
			}
			System.out.println(sb);
		}
	}
}