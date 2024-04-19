import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[] cost;
	static int[] degrees;
	
	static List<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cost = new int[N+1];
			degrees = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N;i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			list = new List[N+1];
			for(int i=1; i<=N;i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				degrees[second]++;
				
				list[first].add(second);
				
			}
			
			st = new StringTokenizer(br.readLine());
			int last = Integer.parseInt(st.nextToken());

			Queue<Integer> queue = new ArrayDeque<>();
			
			int[] max = new int[N+1];
			
			for(int i=1; i<N+1;i++) {
				max[i] = cost[i];
				if(degrees[i]==0) queue.offer(i);
			}
			
			while(!queue.isEmpty()) {
				int idx = queue.poll();
//				System.out.println(idx);
				
				for(int i=0;i<list[idx].size();i++) {
					int next = list[idx].get(i);
					degrees[next]--;
					max[next] = Math.max(max[next], max[idx]+cost[next]);
					if(degrees[next]==0) {
						queue.offer(next);
					}
					
				}

				
//				System.out.println(Arrays.toString(max));
			}
			
			
			sb.append(max[last]+"\n");
		}
		System.out.println(sb);
	}
}