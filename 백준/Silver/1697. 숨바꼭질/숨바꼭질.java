import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * n은 0
 * n*2는 1, 2, 3, 4초 -> 가장 빠름 지점
 *
 *
 */
public class Main {

    static final int MAX_VALUE = 100_001;
    static int n, k;
    static boolean[] visited = new boolean[MAX_VALUE];

    static class Node {
        int idx;
        int n;

        Node(int idx, int n) {
            this.idx = idx;
            this.n = n;
        }
    }

    static int BFS() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(n, 0));
        visited[n] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i=-1; i<2;i++) {
                int idx = node.idx + i;
                if(i==0) {
                    idx*=2;
                }

                if(idx < 0 || idx>=MAX_VALUE || visited[idx]) continue;

                if(idx==k) {
                    return node.n+1;
                }

                queue.offer(new Node(idx, node.n+1));
                visited[idx] = true;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        if(n>=k) {
            System.out.println(n-k);
        }
        else {
            int result = BFS();
            System.out.println(result);
        }


    }
}