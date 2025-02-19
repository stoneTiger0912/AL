import java.io.*;
import java.util.*;

class Solution {
    
    private final int N = 11;
    
    private int[][] graph = new int[N][N];
    private boolean[][][] visited = new boolean[4][N][N];
    private int r = 5, c = 5;
    
    private int result = 0;
    
    private int[] dr = {-1, 0, 0, 1};
    private int[] dc = {0, -1, 1, 0};
    
    private void move(int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        
        if(nr < 0 || nr >= N || nc < 0 || nc >= N) return;
        
        if(!visited[d][nr][nc] && !visited[3-d][r][c]) {
            result += 1;
            visited[d][nr][nc] = true;
            r = nr;
            c = nc;
        }
        else {
            r = nr;
            c = nc;
        }
    }
    
    public int solution(String dirs) {
        
        for(int i=0; i<dirs.length();i++) {
            char d = dirs.charAt(i);
            
            switch(d) {
                case 'U':
                    move(0);
                    break;
                case 'L':
                    move(1);
                    break;
                case 'R':
                    move(2);
                    break;
                case 'D':
                    move(3);
                    break;
            }
            
            System.out.println(result);
        }
        
        int answer = result;
        return answer;
    }
}