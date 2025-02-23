import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static class Node implements Comparable<Node>{
		int num;
		int time;
		
		Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
		
		public int compareTo(Node n) {
			return Integer.compare(this.time, n.time);
		}
	}
	
	static int BFS(int start, int end) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] visited = new int[200_001];
		
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		pq.offer(new Node(start, 0));
		
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			int idx = n.num;
			int time = n.time;
			
			if(idx==end) {
				return time;
			}
			
			for(int i=-1; i<2;i++) {
				int nextIdx = idx;
				int nextTime = time;
				if(i==0) {
					nextIdx += idx;
				}
				else {
					nextIdx += i;
					nextTime += 1;
				}
				
				if(nextIdx >= 200_001 ||nextIdx < 0) continue;
				
				if(visited[nextIdx] > nextTime) {
					visited[nextIdx] = nextTime;
					pq.offer(new Node(nextIdx, nextTime));
				}
				
				
			}
			
			
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int result = BFS(start, end);
		
		System.out.println(result);
		
	}
}