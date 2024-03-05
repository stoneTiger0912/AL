import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] graph;
    static int[] dx = {-1, 0, 1};
    static int count;

    static boolean dfs(int x, int y) {
        for(int i=0; i<3;i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            if(ny > m - 1 || nx <0 || nx > n-1) continue;
            if(graph[nx][ny] == '.') {
                if(ny == m-1) {
                    count++;
                    return true;
                }
                graph[nx][ny] = 'x';

                if(dfs(nx, ny)) return true;
            }

        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][];
        for(int i=0; i<n;i++) {
            st = new StringTokenizer(br.readLine());
            graph[i] = st.nextToken().toCharArray();
        }

        for(int i=0; i<n;i++) {
            dfs(i, 0);
        }
        System.out.println(count);

    }
}