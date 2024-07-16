import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] graph;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }
    static int[][] visited;

    static int max = Integer.MIN_VALUE;

    static void bfs(int startR, int startC) {
        visited = new int[R][C];
        for(int r=0; r<R; r++) {
            Arrays.fill(visited[r], -1);
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startR, startC));
        visited[startR][startC] = 0;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while(--size>=0) {
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];
                    if(nr<0 || nr>=R || nc<0 || nc>=C || graph[nr][nc]=='W' || visited[nr][nc]!=-1) continue;

                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = visited[node.r][node.c] + 1;
                }
            }
            cnt++;
        }

//        System.out.println(startR+" "+startC+" "+cnt);

        max = Math.max(max, cnt-1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];


        for(int r=0; r<R;r++) {
            String tmp = br.readLine();
            for(int c=0; c<C;c++) {
                graph[r][c] = tmp.charAt(c);
            }
        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(graph[r][c] == 'L') {
                    bfs(r,c);
                }
            }
        }

//        for (int[] ints : visited) {
//            for (int anInt : ints) {
//                System.out.print(anInt+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        System.out.println(max);
    }
}


/*
5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW
 */