import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	
	static int back(int N, int r, int c) {
		
		if(N==2) {
			if(r==0 && c==0) {
				return 0;
			}
			else if(r==0 && c==1) {
				return 1;
			}
			else if(r==1 && c==0) {
				return 2;
			}
			else if(r==1 && c==1) {
				return 3;
			}
		}
		
		int check = N / 2;
		int add = (int) Math.pow(check, 2);
		int offset = 0;
		if(r < check && c <check) {
			offset = 0;
		}
		else if(r < check && c >=check) {
			c -= check;
			offset = add;
		}
		else if(r >=check && c < check) {
			r -= check;
			offset = add*2;
		}
		else {
			r -= check;
			c -= check;
			offset = add*3;
		}
//		System.out.println(r+" "+c+" "+offset);
		return offset + back(N/2, r, c);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int N = (int) Math.pow(2, n);
		
		int result = back(N, r, c);
		
		System.out.println(result);
	}
}