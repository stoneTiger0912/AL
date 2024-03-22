import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *덱을 이용하거나 슬라이싱 윈도우일 듯
 */
public class Main {
	
	static int maxResult;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int maxNum = Integer.parseInt(st.nextToken());
		int setNum = Integer.parseInt(st.nextToken());
		int bonus = Integer.parseInt(st.nextToken());

		//시작이 0번째 부터 n번째까지 이고 maxNum개를 읽음 
		int[] list = new int[N];
		
		int[] check = new int[maxNum+1];
		
		for(int i=0; i<N;i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		//초기에 세팅 
		int cnt = 1;
		check[bonus]++;
		
		for(int i=0; i<setNum;i++) {
			if(check[list[i]]==0) cnt++;
			check[list[i]]++;
		}
		
		maxResult = cnt;
		
		for(int i=1;i<N;i++) {
			if(check[list[i-1]]==1) cnt--;

			check[list[i-1]]--;
			
			int next = list[(i+setNum-1)%N];
			
			if(check[next]==0) cnt++;
			check[next]++;
			maxResult = Math.max(maxResult, cnt);
		}
		
		System.out.println(maxResult);
		
	}
}