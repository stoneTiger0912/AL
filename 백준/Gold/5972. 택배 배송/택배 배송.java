import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(cost, o.cost);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		List<Node>[] list = new List[N+1];
		
		for(int i=1; i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, cost));
			list[b].add(new Node(a, cost));
			
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		distance[1] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			int idx = current.idx;
			int dist = current.cost;
			
			if(distance[idx] < dist) continue;
			
			for(Node node: list[idx]) {
				int cost = dist + node.cost;
				
				if(distance[node.idx] > cost) {
					distance[node.idx] = cost;
					pq.offer(new Node(node.idx, cost));
				}
				
			}
			
		}
		
//		System.out.println(Arrays.toString(distance));
		System.out.println(distance[N]);
		
	}
}