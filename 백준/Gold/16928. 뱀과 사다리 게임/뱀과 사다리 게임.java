import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 사 뱀
 * x -> y
 *  
 * 
 * 
 */
public class Main {
	
	static int MAX = 101;
	
	static int N, M;
	static int[] graph = new int[MAX];
	static boolean[] visited = new boolean[MAX];
	
	static int BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		visited[1] = true;
		
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			while(--size>=0) {
				int cur = queue.poll();
				for(int i=1; i<=6;i++) {
					if(cur+i > 100 || visited[cur+i]) continue;
					
					else if(cur+i==100) return cnt;
					
					int next = graph[cur+i];
					if(!visited[next]) {
						queue.offer(next);
						visited[next] = true;
					}
					
				}
			}
			
			cnt++;
			
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<MAX;i++) {
			graph[i] = i;
		}
		
		for(int i=0; i<N+M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x] = y;
			
		}
		
//		System.out.println(Arrays.toString(graph));
		
		int result = BFS();
		
		System.out.println(result+1);
		
		
	}
}