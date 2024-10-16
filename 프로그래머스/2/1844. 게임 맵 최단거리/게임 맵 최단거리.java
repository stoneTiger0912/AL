import java.util.*;
import java.io.*;

class Solution {
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    private int BFS(int[][] graph, int R, int C) {
        boolean[][] visited = new boolean[R][C];
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        
        int cnt = 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            while(--size>=0) {
                Node node = queue.poll();
                // System.out.println(node.r +" "+node.c);
                
                if(node.r == R-1 && node.c == C-1) {
                    return cnt;
                }
                
                for(int d=0; d<4;d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];
                    
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 0 || visited[nr][nc]) {
                        continue;
                    }
                    
                    visited[nr][nc] = true;
                    queue.offer(new Node(nr, nc));
                    
                }
            }
            
            cnt++;
        }
        
        
        return -1;
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        int r = maps.length;
        int c = maps[0].length;
        
        answer = BFS(maps, r, c);
        
        // System.out.println(answer);
        return answer;
    }
}

class Node {
    int r;
    int c;
    
    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}