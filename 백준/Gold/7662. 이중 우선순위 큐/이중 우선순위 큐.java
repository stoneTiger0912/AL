import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			TreeMap<Integer, Integer> pq = new TreeMap<>(
					(o1, o2) -> Integer.compare(o1, o2)
					);
			
			
			for(int i=0; i<N;i++) {
				st = new StringTokenizer(br.readLine());
				
				String way = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
//				if(!pq.isEmpty()) System.out.println(pq.firstKey());
				
				if(way.equals("I")) {
					pq.put(num, pq.getOrDefault(num, 0)+1);
				}
				else {
					if(num==1) {
						if(pq.isEmpty()) continue;
						
						if(pq.get(pq.lastKey()) > 1) pq.put(pq.lastKey(), pq.get(pq.lastKey())-1);
						else pq.remove(pq.lastKey());
					}
					else {
						if(pq.isEmpty()) continue;

						if(pq.get(pq.firstKey()) > 1) pq.put(pq.firstKey(), pq.get(pq.firstKey())-1);
						else pq.remove(pq.firstKey());
					}
				}
				
			}
			
			if(pq.isEmpty()) {
				sb.append("EMPTY").append("\n");
				continue;
			}
			
			int min = pq.firstKey();
			int max = pq.lastKey();
			
			sb.append(max).append(" ").append(min).append("\n");
			
		}
		System.out.println(sb);
	}
}