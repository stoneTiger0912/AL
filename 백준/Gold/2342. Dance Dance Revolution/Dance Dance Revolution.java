import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 * 중앙에서 한쪽으로 갈때 2
 * 인접한 곳 3
 * 반대편 4
 * 0, 0에서 시작 -> 같은 곳은 그대로
 * 길이 최대 10만
 */
public class Main {
	
	//r -> c로 갈 경우 드는 비용
	static int[][] nextStep = {
			{1, 2, 2, 2, 2},
			{0, 1, 3, 4, 3},
			{0, 3, 1, 3, 4},
			{0, 4, 3, 1, 3},
			{0, 3, 4, 3, 1}
	};
	
	static List<Integer> list = new ArrayList<>();
	
	static int[][][] dp;
	
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		while(true) {
			int num = Integer.parseInt(st.nextToken());
			if(num==0) break;
			list.add(num);
		}
		
		size = list.size();
		if(size==0) System.out.println(0);
		else {
			dp = new int[size][5][5];
			int answer = search(0, 0, 0);
			System.out.println(answer);
			
		}
	}
	
	static int search(int idx, int l, int r) {
        if(idx == size)	//발판 모두 밟았을 때
            return 0;

        if(dp[idx][l][r] != 0)	//이미 밟아본 발판일 경우
            return dp[idx][l][r];

        int nxt = list.get(idx);
        
        
        dp[idx][l][r] = Math.min(search(idx+1, nxt, r) + nextStep[l][nxt],  search(idx+1, l, nxt) + nextStep[r][nxt]);
        

        return dp[idx][l][r];
    }

}