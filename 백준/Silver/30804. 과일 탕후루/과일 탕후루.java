import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
 * 투 포인터 사용
 * 맨 마지막과 맨 처음에 포인터를 줘서 개수가 적은 걸 먼저 지움
 * 
 * 단 투포인터로 할 경우 아래와 같은 문제가 발생
 * 5 5 5 1 1 1 1 2 1 -> 이거의 경우 5551111로 8개가나옴
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			int n = Integer.parseInt(st.nextToken());
			
			input[i] = n;
			
		}
		
		int result = 1;
		
		int left = 0;
		int right = 0;
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		
		while(right < N) {
			
//			System.out.println(left+" "+right);
//			System.out.println(result);
			
			if(map.size() <= 2) {
				if(map.containsKey(input[right])) {
					map.put(input[right], map.get(input[right])+1);
				}
				else {
					if(map.size()==2) {
						int num = map.get(input[left]);
//						System.out.println("num: "+num);
						
						if(num==1) map.remove(input[left]);
						
						else {
							map.put(input[left], num-1);
						}
						
						left++;
						continue;
					}
					
					map.put(input[right], 1);
				}
				
				result = Math.max(result, right-left+1);
				
				right++;
			}
			else {
				
				int num = map.get(input[left]);
//				System.out.println("num: "+num);
				
				if(num==1) map.remove(input[left]);
				
				else {
					map.put(input[left], num-1);
				}
				
				left++;
			}

		}
		
		System.out.println(result);
		

	}
}