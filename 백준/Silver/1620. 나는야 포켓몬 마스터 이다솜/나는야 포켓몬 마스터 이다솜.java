import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> stringKeyMap = new HashMap<>();
		Map<Integer, String> integerKeyMap = new HashMap<>();
		
		for(int i=1; i<=N;i++) {
			String poketmon = br.readLine();
			
			stringKeyMap.put(poketmon, i);
			integerKeyMap.put(i, poketmon);
		}
		
		for(int i=0; i<M;i++) {
			String input = br.readLine();
			if(input.charAt(0)>='0' && input.charAt(0)<='9') {
				sb.append(integerKeyMap.get(Integer.parseInt(input))).append("\n");
			}
			else {
				sb.append(stringKeyMap.get(input)).append("\n");
			}
		}
		System.out.println(sb);
	}
}