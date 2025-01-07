import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * MST로 최소신장 트리를 만든다음 가장 큰 수를 빼면 될 것 같음
 */
public class Main {
	
	static int N;
	static int[] parent;
	
	static class Node implements Comparable<Node> {
		int a;
		int b;
		int cost;
		
		Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		PriorityQueue<Integer> costList = new PriorityQueue<>();
		
		parent = new int[N+1];
		for(int i=1; i<=N;i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			Node node = new Node(a, b, cost);
			
			pq.offer(node);
		}
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			boolean check = union(node.a, node.b);
			
			if(check) {
				costList.offer(node.cost);
				
			}
			
		}
		
		
		int result = 0;
		
		int size = costList.size();
		
		while(--size>=1) {
			result += costList.poll();
		}
		System.out.println(result);
		
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA < parentB) parent[parentA] = b;
		else if(parentA > parentB) parent[parentB] = a;
		else {
			return false;
		}
		
		return true;
		
	}
}