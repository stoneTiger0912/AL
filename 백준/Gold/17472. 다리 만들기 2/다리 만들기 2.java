import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int R, C;
    
    static int[][] graph;
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r  = r;
            this.c = c;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static int[] parent;
    
    static int find(int x) {
        if(x!= parent[x]) parent[x] = find(parent[x]);
        
        return parent[x];
    }
    
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a==b) return true;
        if(a < b) parent[a] = b;
        else parent[b] = a;
        
        return false;
    }
    
    
    static void BFS(int r, int c, int num) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r, c));
        graph[r][c] = num;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int curR = node.r;
            int curC = node.c;
            for(int d=0; d<4;d++) {
                int nr = curR+dr[d];
                int nc = curC+dc[d];
                if(nr < 0|| nr >=R || nc < 0 || nc >= C || graph[nr][nc] != 1) continue;
                queue.offer(new Node(nr, nc));
                graph[nr][nc] = num;
                
            }
        }
    }
    
    static int[][] distance;
    
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
            }    
        }
        
        //flood fill
        int cnt = 2;
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(graph[r][c] != 1) continue;
                BFS(r, c, cnt);
                cnt++;
            }    
        }
        distance = new int[cnt][cnt];
        parent = new int[cnt];
        for(int i=0; i<cnt;i++) {
            parent[i] = i;
        }

        for(int r=0; r<cnt;r++) {
            for(int c=0; c<cnt;c++) {
                if(r==c) distance[r][c] = 0;
                else distance[r][c] = Integer.MAX_VALUE;
            }    
        }
//        print(graph);
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(graph[r][c] == 0) continue;
                for(int d=0;d<4;d++) {
                    getDistance(r, c, d, graph[r][c]);
                }
            }    
        }
        
//        print(distance);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)-> o1.len - o2.len); 
        
        for(int r = 0; r<cnt;r++)  {
            for(int c = 0; c<cnt;c++)  {            
                if(r==c || distance[r][c]==Integer.MAX_VALUE) continue;
                if(distance[r][c] == 1) continue;
                
                pq.offer(new Edge(r, c, distance[r][c]));
                
            }
        }
        
        
        int num = 0;
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
//            System.out.println(edge.a+" "+edge.b+" "+edge.len);
            boolean flag = union(edge.a, edge.b);
            if(flag) continue;
            else {
                num += edge.len;
            }
//            System.out.println(Arrays.toString(parent));
        }
        
        int check = find(2);
        
        for(int i=2; i<cnt;i++) {
//        	System.out.println(find(i));
        	if(check!=find(i)) {
        		System.out.println(-1);
        		return;
        	}
        }
        
        System.out.println(num==0 ? -1:num);
        
        
    }
    
    static class Edge {
        int a;
        int b;
        int len;
        
        Edge(int a, int b, int len) {
            this.a = a;
            this.b = b;
            this.len = len;
        }
    }
    
    static void getDistance(int r, int c, int d, int num) {
        int nr = r+dr[d];
        int nc = c+dc[d];
        int cnt = 0;
        while(true) {

            if(nr < 0|| nr >=R || nc < 0 || nc >= C) return;
            
            if(graph[nr][nc]==num) return;
            else if(graph[nr][nc]==0) {

                cnt++;
                nr += dr[d];
                nc += dc[d];
                
            }
            else {
                int next = graph[nr][nc];
                if(cnt==1) return;
                distance[num][next] = Math.min(distance[num][next], cnt);
                return;
            }
            
        }
    }
    
    
    static void print(int[][] graph) {
        for(int[] is: graph) {
            for(int i: is) {
                if(i==Integer.MAX_VALUE) System.out.print(0+" ");
                else System.out.print(i+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
