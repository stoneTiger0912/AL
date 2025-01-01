import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,m,i,j,s;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for(i=0;i<m;i++) arr[i] = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		for(i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(j=1;j<=n;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		s = 0;
		for(i=1;i<m;i++) s += map[arr[i-1]][arr[i]];
		System.out.println(s);
	}
}