import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static Set<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] degrees = new int[N+1];
		list = new Set[N+1];
		for(int i=1; i<=N;i++) {
			list[i] = new HashSet<>();
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			list[first].add(second);
			
			degrees[second]++;
			
		}
		//System.out.println(Arrays.toString(degrees));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N;i++) {
			if(degrees[i]==0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int num = pq.poll();
			sb.append(num).append(" ");
			for(int i:list[num]) {
				degrees[i]--;
				if(degrees[i]==0) pq.offer(i);
			}

		}
		
		System.out.println(sb);
	}
}