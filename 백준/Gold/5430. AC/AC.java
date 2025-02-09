import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		A: for(int t=1; t<=T;t++) {
			ArrayDeque<Integer> deque = new ArrayDeque<>();

			String input = br.readLine();
			int n = Integer.parseInt(br.readLine());

			String[] numList = br.readLine().replace("[", "").replace("]", "").split(",");

			if(n!=0) {
				for(int i=0; i<numList.length;i++) {
//					System.out.println(Integer.parseInt(numList[i]));
					deque.offer(Integer.parseInt(numList[i]));
				}
			}
			boolean flag = false;
			for(int i=0; i<input.length();i++) {
				char a = input.charAt(i);
				if(a=='D') {
					if(deque.size()>=1) {
						if(!flag) deque.pollFirst();
						else deque.pollLast();
					}
					else {
						sb.append("error").append("\n");
						continue A;
					}
				}
				else flag = !flag;
			}
			
			sb.append("[");
			int size = deque.size();
			for(int i=0; i<size;i++) {
				if(!flag) sb.append(deque.pollFirst());
				else sb.append(deque.pollLast());
				if(i!=size-1) {
					sb.append(",");
				}
			}
			sb.append("]");
			sb.append("\n");
			
			
		}
		System.out.println(sb);
	}
}