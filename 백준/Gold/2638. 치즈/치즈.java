import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *visited를 2번 만나면 끝으로 하면 될듯
 * N은 최대 100x100이므로 
 *8 9
    0 0 0 0 0 0 0 0 0
    0 0 0 1 1 0 0 0 0
    0 0 0 1 1 0 1 1 0
    0 0 1 1 1 1 1 1 0
    0 0 1 1 1 1 1 0 0
    0 0 1 1 0 1 1 0 0
    0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0
 */
public class Main {
    
    static int R, C;
    static int[][] graph;
    
    static int cheeseCnt;
    
    static int[][] cnt;
    static boolean[][] visited;
    
    static int count = 0;
    
    
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
    
    static void BFS() {
        Queue<Node> queue = new ArrayDeque<>();
        count = 0;
        while(cheeseCnt > 0) {
            visited = new boolean[R][C];
            cnt = new int[R][C];
            queue.offer(new Node(0, 0));
            visited[0][0] = true;
            Queue<Node> cheeseList = new ArrayDeque<>();
            
            while(!queue.isEmpty()) {
                int size = queue.size();
                while(--size>= 0) {
                    Node current = queue.poll();
                    int r = current.r;
                    int c = current.c;
                    for(int d=0; d<4;d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                        
                        if(graph[nr][nc] == 1) {
                            if(cnt[nr][nc] == 1) {
                                visited[nr][nc] = true;
                                cnt[nr][nc]++;

                                cheeseList.offer(new Node(nr, nc));
                            }
                            else cnt[nr][nc]++;
                        }
                        else {
                            queue.offer(new Node(nr, nc));
                            visited[nr][nc] = true;
                        }
                        
                    }
                }
            }
            
            while(!cheeseList.isEmpty()) {
            	Node currentNode = cheeseList.poll();
            	graph[currentNode.r][currentNode.c] = 0;
            	cheeseCnt--;
            }
//            print();
            count++;
            
        }
    }
    
    static void print() {
    	for(int[] is:graph) {
    		for(int i:is) {
    			System.out.print(i+" ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        graph = new int[R][C];
        
        for(int r=0; r<R;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                if(graph[r][c] == 1) cheeseCnt++;
            }
        }
        
        BFS();
        
        System.out.println(count);
    }
}