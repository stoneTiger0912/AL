import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX = Integer.MAX_VALUE;
	
	static List<Node>[] list;
	static int N;
	
	static class Node implements Comparable<Node> {
		int idx;
		int distance;
		
		Node(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}
		
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static int Dijstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		int[] visited = new int[N+1];
		Arrays.fill(visited, MAX);
		visited[start] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			int idx = n.idx;
			int distance = n.distance;
			
			if(visited[idx] < distance) continue;
			
//			visited[idx] = distance;
			
			for(Node node: list[idx]) {
				if(visited[node.idx] > distance + node.distance) {
					visited[node.idx] = distance + node.distance;
					pq.offer(new Node(node.idx, distance + node.distance));
					
//					System.out.println(Arrays.toString(visited));
				}
			}
			
		}
		
		return visited[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		for(int i=1; i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(e, cost));
			
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int result = Dijstra(start, end);
		System.out.println(result);
		
	}
}
