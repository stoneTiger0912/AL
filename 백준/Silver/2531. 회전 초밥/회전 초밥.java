import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2포인터로 1인덱스씩 움직이면서 확인 
 * 원형 큐의 형태이므로 배열 2개를 붙여서 사용
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] input = new int[2*N];
		
		//원형큐이므로
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			input[i] = tmp;
			input[N+i] = tmp;
		}
		
		int start = 0;
		int end = 0;
		int cnt = 0;
		//개수로 하기
		int[] visited = new int[d+1];
		
		int result = 0;
		
		while(start!=N) {
			//연속 k가지 먹을때 까지
			if(end-start < k) {
				int n = input[end];
				if(visited[n]==0) cnt++;
				
				visited[n] += 1;
				end++;
			}
			else {
				if(visited[c]==0) result = Math.max(cnt+1, result);
				else result = Math.max(cnt, result);
//				System.out.println(start+" "+end);
//				System.out.println(result);
				visited[input[start]] -= 1;
				if(visited[input[start]]==0) cnt--;
				start++;
			}
		}
		
		System.out.println(result);
		
	}
}