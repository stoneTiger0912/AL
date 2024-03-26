import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 1. 무게 보다 낮은 경우
 * 2. 무게 보다 높은 경우
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] inputList = new int[N][2];
		
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());

			//0은 무게 1은 V벨류
			inputList[i][0] = Integer.parseInt(st.nextToken());
			inputList[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] pre = new int[K+1];
		int[] next = new int[K+1];
		
		
		for(int i=0; i<N;i++) {
			int w = inputList[i][0];
			for(int j=0; j<K+1;j++) {
				if(inputList[i][0]> j) continue;
				
				next[j] = Math.max(pre[j], pre[j-w]+inputList[i][1]);
			}
//			for(int j=0; j<K+1;j++) {
//				System.out.print(next[j]+" ");
//			}
//			System.out.println();
			System.arraycopy(next, 0, pre, 0, K+1);
		}
		System.out.println(next[K]);
	}
}