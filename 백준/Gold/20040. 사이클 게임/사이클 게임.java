import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	static int find(int x) {
		if(parent[x]!=x) parent[x] = find(parent[x]);
		
		return parent[x];
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return true;
		else {
			if(a < b) parent[a] = b;
			else parent[b] = a;
			
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		
		for(int i=0; i<n;i++) {
			parent[i] = i;
		}
		
		int result = 0;
		
		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());			
			
			if(union(a, b) && result==0) result = i+1;
			
		}
		System.out.println(result);
	}
}