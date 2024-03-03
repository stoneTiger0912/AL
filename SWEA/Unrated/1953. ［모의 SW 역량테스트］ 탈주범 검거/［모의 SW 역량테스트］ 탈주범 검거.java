import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * The type Swea.
 * 테케
 * 세로 가로 맨홀세로 맨홀가로 시간L
 *
 * 개수 출력
 * 0은 없음
 * 1은 상하좌우
 * 2는 상하
 * 3은 좌우
 * 4는 상우
 * 5는 하우
 * 6은 하좌
 * 7은 상좌
 */
public class Solution {

    static int R, C, startR, startC, time;

    static int[][] graph;
    static boolean[][] visited;

    static int[][][] delta = {
            null,
            {{-1, 0}, {1, 0}, {0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {0, 1}},
            {{1, 0},  {0, 1}},
            {{1, 0}, {0, -1}},
            {{-1, 0}, {0, -1}}
    };

    static void BFS() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startR, startC));
        visited[startR][startC] = true;

        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (--size >= 0) {
                Node current = queue.poll();
                int r = current.r;
                int c = current.c;
                int dir = graph[r][c];
                A: for(int d=0;d < delta[dir].length;d++) {
                    int nr = r + delta[dir][d][0];
                    int nc = c + delta[dir][d][1];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;

                    if(graph[nr][nc]==0) continue;

                    else {
                        int nd = graph[nr][nc];
                        boolean flag = false;
                        switch (dir) {
                            case 1:
                                //상에
                                flag =  check(d, nd);
                                break;
                            case 2:
                                if(d==0) flag = check(0, nd);
                                else if(d==1) flag = check(1, nd);
                                break;
                            case 3:
                                if(d==0) flag = check(2, nd);
                                else if(d==1) flag = check(3, nd);
                                break;
                            case 4:
                                if(d==0) flag = check(0, nd);
                                else if(d==1) flag = check(3, nd);
                                break;
                            case 5:
                                if(d==0)  flag = check(1, nd);
                                else if(d==1) flag = check(3, nd);
                                break;
                            case 6:
                                if(d==0) flag = check(1, nd);
                                else if(d==1) flag = check(2, nd);
                                break;
                            case 7:
                                if(d==0) flag = check(0, nd);
                                else if(d==1) flag = check(2, nd);
                                break;

                        }
                        if(!flag) continue;
                        queue.offer(new Node(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            cnt++;
            if(cnt==time-1) return;
        }


    }

    static boolean check(int d, int nd) {
        switch (d) {
            //상 일경우
            case 0:
                if(nd==1 || nd ==2 || nd == 5|| nd ==6) return true;
                break;
            case 1:
                if(nd==1 || nd ==2 || nd == 4|| nd ==7) return true;
                break;
            case 2:
                if(nd==1 || nd ==3 || nd == 4|| nd ==5) return true;
                break;
            case 3:
                if(nd==1 || nd ==3 || nd == 6|| nd ==7) return true;
                break;

        }
        return false;
    }

    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            graph = new int[R][C];
            visited = new boolean[R][C];

            for(int r=0; r<R;r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<C;c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            int result = 0;
            if(time==1) result = 1;
            
            else {
                BFS();

                for(int r=0; r<R;r++) {
                    for (int c=0; c<C;c++) {
                        if(visited[r][c]) result += 1;
                    }
                }
            }




            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}