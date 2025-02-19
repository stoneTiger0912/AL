import java.io.*;
import java.util.*;

class Solution {
    
    private int[][] graph;
    private int R, C;
    
    private boolean[][] visited;
    
    private class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    //상 좌 우 하
    private int[] dr = {-1, 0, 0, 1};
    private int[] dc = {0, -1, 1, 0};
    
    private int BFS(int startR, int startC) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startR, startC));
        
        int cnt = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            while(--size>=0) {
            
                Node n = queue.poll();
                
                A: for(int d=0; d<4;d++) {
                    int nr = n.r;
                    int nc = n.c;
                    
                    while(true) {
                        nr += dr[d];
                        nc += dc[d];
                        //경계 밖으로 빠져 나오거나 벽
                        if(nr < 0 || nr >=R || nc < 0 || nc >= C ||graph[nr][nc] == 1) {
                            nr -= dr[d];
                            nc -= dc[d];
                            break;
                        }
                    }

                    if(graph[nr][nc]==2) {
                        return cnt+1;
                    }
                    
                    else {
                        if(visited[nr][nc]) continue;
                        else {
                            visited[nr][nc] = true;
                            queue.offer(new Node(nr, nc));
                        }
                    }
                }
            
                
            }
            
            cnt++;
        }
        
        return -1;
    }
    
    public int solution(String[] board) {
        
        R = board.length;
        C = board[0].length();
        
        graph = new int[R][C];
        visited = new boolean[R][C];
        
        int startR = 0;
        int startC = 0;
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                char tmp = board[r].charAt(c);
                switch(tmp) {
                    case '.':
                        graph[r][c] = 0;
                        break;
                    case 'D':
                        graph[r][c] = 1;
                        break;
                    case 'R':
                        startR = r;
                        startC = c;
                        graph[r][c] = 0;
                        break;
                    case 'G':
                        graph[r][c] = 2;
                        break;
                }
            }
        }
        
        print();
        
        int answer = BFS(startR, startC);
        
        return answer;
    }
    
    private void print() {
        for(int[] r: graph) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}