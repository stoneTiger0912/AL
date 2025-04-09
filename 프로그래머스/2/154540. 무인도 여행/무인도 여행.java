import java.util.*;
import java.io.*;
class Solution {
    
    boolean[][] visited;
    int[][] graph;
    int R, C;
    
    private class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    private int BFS(int startR, int startC) {
        Queue<Node> queue = new ArrayDeque<>();
        
        queue.offer(new Node(startR, startC));
        visited[startR][startC] = true;
        int cnt = graph[startR][startC];
        
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            
            int r = n.r;
            int c = n.c;
            
            for(int d=0; d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if(graph[nr][nc]==0 || visited[nr][nc]) continue;
                
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
                cnt += graph[nr][nc];
                
            }
            
        }
        
        return cnt;
        
    }
    
    public int[] solution(String[] maps) {
        
        R = maps.length;
        C = maps[0].length();
        
        graph = new int[R][C];
        visited = new boolean[R][C];
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                int tmp = 0;
                
                if(maps[r].charAt(c)!='X') {
                    tmp = maps[r].charAt(c) - '0';
                }
            
                graph[r][c] = tmp;
            }
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(graph[r][c]== 0 || visited[r][c]) continue;
                
                int cnt = BFS(r, c);
                
                pq.offer(cnt);
            }
        }
        
        int[] answer;
        
        if(pq.size()==0) {
            answer = new int[1];
            answer[0] = -1;
        }
        else {
            int idx = 0;
            answer = new int[pq.size()];
            while(!pq.isEmpty()) {
                answer[idx++] = pq.poll();
            }
        }
        
        
        return answer;
    }
}