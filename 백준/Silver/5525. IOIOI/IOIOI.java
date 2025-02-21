import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[len];
		
		String tmp = br.readLine();
		for(int i=0; i<tmp.length();i++) {
			char a = tmp.charAt(i);
			
			arr[i] = a == 'I' ? 1 : 0;
		}
		
		int[] key = new int[2*N+1];
		for(int i=0; i<2*N+1;i++) {
			key[i] = i % 2 == 0 ? 1 : 0;
		}
		
		
		int result = 0;
		int cnt = 0;
		
		for(int i=0; i<len;i++) {
			if(i + 2 >= len) {
				break;
			}
			
			if(arr[i] == 1 && arr[i+1] == 0 && arr[i+2]==1) {
				cnt++;
				i++;
				
				if(cnt == N) {
					result++;
					cnt--;
				}
			}
			else {
				cnt = 0;
			}
		}
		
//		int start = 0;
//		int end = 0;
//		int cnt = 0;
//		A: while(start<len) {
//			
//			if(arr[start]==0) start++;
//			else {
//				
////				System.out.println(start);
//				
//				int cnt = 0;
//				end = start+1;
//				
//				while(true) {
//					
//					
//					if(cnt==N) {
//						result++;
////						System.out.println(start+" result");
//						break;
//					}
//					
//					if(end+1 >= len) break;
//					
//					if(arr[end]==0 && arr[end+1]==1) {
//						cnt++;
//						end += 2;
//					}
//					else {
//						start++;
//						continue A;
//					}
//					
//					
//				}
//				
//				start += 2;
//				
//				
//			}
//		}
		
		
//		A: for(int i=0; i<len-(2*N+1);i++) {
//			for(int j=0; j<(2*N+1);j++) {
//				if(arr[j+i]!=key[j]) continue A; 
//			}
////			System.out.println(i);
//			result += 1;
//		}
//		
		System.out.println(result);
		
	}
}