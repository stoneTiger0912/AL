import java.util.*;
import java.io.*;

class Solution {
    
    private int[][] graph;
    private int R, C;
    private int endR, endC;
    
    class Node {
        int r;
        int c;
        boolean flag;
        
        Node(int r, int c, boolean flag) {
            this.r = r;
            this.c = c;
            this.flag = flag;
        }
    }
    
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    private int BFS(int startR, int startC) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startR, startC, false));
        
        boolean[][][] visited = new boolean[2][R][C];
        visited[0][startR][startC] = true;
        
        int cnt = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size>=0) {
                Node node = queue.poll();
                int curR = node.r;
                int curC = node.c;
                boolean flag = node.flag;

                for(int d=0; d<4;d++) {
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];
                    
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) continue;
                    
                    if(flag) {
                        if(visited[1][nr][nc]) continue;
                        
                        if(nr == endR && nc == endC) {
                            return cnt+1;
                        }
                        
                        queue.offer(new Node(nr, nc, flag));
                        visited[1][nr][nc] = true;
                        
                    }
                    else {
                        if(visited[0][nr][nc]) continue;
                        
                        if(graph[nr][nc]==2) {
                            if(visited[1][nr][nc]) continue;
                            visited[1][nr][nc] = true;
                            queue.offer(new Node(nr, nc, true));
                        }
                        else {
                            queue.offer(new Node(nr, nc, false));
                            visited[0][nr][nc] = true;
                        }
                    }
                    
                    
                }
            }
            cnt++;
        }
        
        return -1;
    } 
    
    public int solution(String[] maps) {
        
        this.R = maps.length;
        this.C = maps[0].length();
        this.graph = new int[R][C];
        
        int startR = 0;
        int startC = 0;
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                char cur = maps[r].charAt(c);
                switch(cur) {
                    case 'S':
                        startR = r;
                        startC = c;
                        graph[r][c] = 0;
                        break;
                    case 'O':
                        graph[r][c] = 0;
                        break;
                    case 'X':
                        graph[r][c] = 1;
                        break;
                    case 'L':
                        graph[r][c] = 2;
                        break;
                    case 'E':
                        this.endR = r;
                        this.endC = c;
                        graph[r][c] = 0;
                        break;
                }
            }
        }
        
//        print();
        
        int answer = BFS(startR, startC);
        return answer;
    }
    
    private void print() {
        for(int[] r: this.graph) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}