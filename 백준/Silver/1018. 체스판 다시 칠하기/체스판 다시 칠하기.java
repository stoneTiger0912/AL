import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		for(int r=0; r<R;r++) {
			String tmp = br.readLine();
			for(int c=0; c<C;c++) {
				if(tmp.charAt(c)=='W') {
					graph[r][c] = 0;
				}
				else graph[r][c] = 1;
			}
		}
		
		int[][][] reArt = new int[2][R][C];
		
		int result = Integer.MAX_VALUE;
		
		for(int i=0; i<2;i++) {
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					//맨 왼쪽과 같은 경우
					if((r+c) % 2==0) {
						if(graph[r][c]!=i) {
							reArt[i][r][c] = -1;
						}
					}
					else {
						if(graph[r][c]==i) {
							reArt[i][r][c] = -1;
						}
					}
				}
			}
			
			for(int r=0; r<=R-8;r++) {
				A: for(int c=0; c<=C-8;c++) {
					int cnt = 0;
					for(int x=0; x<8;x++) {
						for(int y=0; y<8;y++) {
							if(reArt[i][r+x][c+y]==-1) cnt++;
							
							if(cnt>result) continue A;
						}
					}
					
					result = Math.min(result, cnt);
				}
			}
		}
		
		System.out.println(result);
	}
}