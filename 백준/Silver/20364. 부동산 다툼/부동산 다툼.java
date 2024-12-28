import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] graph;
	static int N, Q;
	
	static int check(int idx) {
		
		int res = 0;
		int tmp = idx;
		
		while(tmp>0) {
			//이미 존재하는 경우
			if(graph[tmp]) {
				res = tmp;
			}
			
			//홀수일 경우
			if(tmp % 2 == 1) {
				tmp--;
			}
			
			tmp = tmp / 2;
		}
		
		if(res==0) {
			graph[idx] = true;
		}
		
		return res;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N+1];
		
		for(int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			int res = check(idx);
			sb.append(res).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}