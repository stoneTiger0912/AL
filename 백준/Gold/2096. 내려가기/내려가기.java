import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
1칸씩 내려가는데
대각선이랑 아래만 가능
최대값이랑 최소값을 구하라
N 최대 100000

dp[][]

 */
public class Main {

    static int N;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N+1][3];
        int[][] minDp = new int[N+1][3];
        int[][] maxDp = new int[N+1][3];
        for(int r=0; r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<3;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

//        int[][] dp = new int[N+1][3];
//        for(int r=0; r<N;r++) {
//            for(int c=0; c<3;c++) {
//                minDp[r][c] = graph[r][c];
//                maxDp[r][c] = graph[r][c];
//            }
//        }

        System.arraycopy(graph[0], 0, minDp[0], 0, 3);
        System.arraycopy(graph[0], 0, maxDp[0], 0, 3);

        for(int r=0; r<N-1;r++) {
            for(int c=0; c<3;c++) {
                maxDp[r+1][c] = Math.max(maxDp[r+1][c], maxDp[r][c]+graph[r+1][c]);
                if(minDp[r+1][c]==0) minDp[r+1][c] = minDp[r][c] + graph[r+1][c];
                else minDp[r+1][c] = Math.min(minDp[r+1][c], minDp[r][c]+graph[r+1][c]);
                switch (c) {
                    case 1:
                        maxDp[r+1][c-1] = Math.max(maxDp[r+1][c-1], maxDp[r][c]+graph[r+1][c-1]);
                        if(minDp[r+1][c-1]==0) minDp[r+1][c-1] = minDp[r][c] + graph[r+1][c-1];
                        else minDp[r+1][c-1] = Math.min(minDp[r+1][c-1], minDp[r][c]+graph[r+1][c-1]);
                    case 0:
                        maxDp[r+1][c+1] = Math.max(maxDp[r+1][c+1], maxDp[r][c]+graph[r+1][c+1]);
                        if(minDp[r+1][c+1]==0) minDp[r+1][c+1] = minDp[r][c] + graph[r+1][c+1];
                        else minDp[r+1][c+1] = Math.min(minDp[r+1][c+1], minDp[r][c]+graph[r+1][c+1]);
                        break;
                    case 2:
                        maxDp[r+1][c-1] = Math.max(maxDp[r+1][c-1], maxDp[r][c]+graph[r+1][c-1]);
                        if(minDp[r+1][c-1]==0) minDp[r+1][c-1] = minDp[r][c] + graph[r+1][c-1];
                        else minDp[r+1][c-1] = Math.min(minDp[r+1][c-1], minDp[r][c]+graph[r+1][c-1]);

                        break;
                }
            }
        }

        int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));
        int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));

        System.out.println(max+" "+min);

    }
}