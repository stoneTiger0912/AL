import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


	static int[] selected = new int[2];

	static class Game {
		int a;
		int b;

		Game(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	static Game[] gameList = new Game[16];
	static int gameCnt = 0;

	static void comb(int idx, int cnt) {

		if(cnt==2) {
			int a = selected[0];
			int b = selected[1];
			gameList[gameCnt++] = new Game(a, b);
			return;
		}

		for(int i=idx; i<6;i++) {
			selected[cnt] = i;
			comb(i+1, cnt+1);
		}
	}

	static int result;

	static void backTrack(int idx, int[][] regionList) {

		if(idx==gameCnt) {
			result = 1;
			return;
		}


		int a = gameList[idx].a;
		int b = gameList[idx].b;

		for(int i=0; i<3;i++) {
			//a가 이김
			if(i==0) {
				if(regionList[a][0]==0 || regionList[b][2]==0) continue;

				regionList[a][0]--;
				regionList[b][2]--;
				backTrack(idx+1, regionList);
				regionList[a][0]++;
				regionList[b][2]++;

			}
			//비김
			else if(i==1) {
				if(regionList[a][1]==0 || regionList[b][1]==0) continue;

				regionList[a][1]--;
				regionList[b][1]--;
				backTrack(idx+1, regionList);
				regionList[a][1]++;
				regionList[b][1]++;
			}
			//a가 짐
			else {
				if(regionList[a][2]==0 || regionList[b][0]==0) continue;

				regionList[a][2]--;
				regionList[b][0]--;
				backTrack(idx+1, regionList);
				regionList[a][2]++;
				regionList[b][0]++;
			}
		}

	}

	static int[][] regionList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		comb(0, 0);

		//		for(int i=0;i<gameCnt;i++) {
		//			System.out.println(gameList[i].a+" "+gameList[i].b);
		//		}

		//1은 윈 2는 무승부 3은 패배

		for(int i=0; i<4;i++) {
			regionList = new int[6][3];
			result = 0;
			st = new StringTokenizer(br.readLine());
			boolean flag = false;

			for(int j=0; j<6;j++) {
				regionList[j][0] = Integer.parseInt(st.nextToken());
				regionList[j][1] = Integer.parseInt(st.nextToken());
				regionList[j][2] = Integer.parseInt(st.nextToken());
				if(regionList[j][0]>5 || regionList[j][1]>5 || regionList[j][2]>5) flag = true;
			}
			if(flag) sb.append(0).append(" ");
			else {
				backTrack(0, regionList);
				sb.append(result).append(" ");
			}
		}

		System.out.println(sb);


	}
}