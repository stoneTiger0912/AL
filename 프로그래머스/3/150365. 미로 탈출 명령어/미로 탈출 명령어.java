import java.util.*;
import java.io.*;
class Solution {
    
    private int[][] graph;
    private int R, C;
    private int endR, endC;
    private String result = "";
    
    // d > l > r > u
    private int[] dr = {1, 0, 0, -1};
    private int[] dc = {0, -1, 1, 0};
    
    private void DFS(int r, int c, int idx, int[] array, int k) {
        
        if(!result.equals("")) {
            return;
        }
        if(((Math.abs(r-endR) + Math.abs(c-endC)) % 2) == 0) {
            if((k - (idx+1)) % 2==1) {
                return;
            }
        } 
        if(Math.abs(r-endR) + Math.abs(c-endC) > k - (idx+1)) return;
        
        if(idx==k-1) {
            if(r!=endR ||c !=endC) return;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<k;i++) {
                switch(array[i]) {
                    case 0:
                        sb.append("d");
                        break;
                    case 1:
                        sb.append("l");
                        break;
                    case 2:
                        sb.append("r");
                        break;
                    case 3:
                        sb.append("u");
                        break;
                }
            }
            result = sb.toString();
            
            return;
        }
        
        for(int d=0; d<4;d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr < 0 || nr >= R || nc < 0 || nc>=C) continue;
            
            array[idx+1] = d;
            DFS(nr, nc, idx+1, array, k);
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        R = n;
        C = m;
        graph = new int[R][C];
        endR = r-1;
        endC = c-1;
        
        DFS(x-1, y-1, -1, new int[k], k);
        
        String answer = result;
        if(answer.equals("")) answer = "impossible";
        return answer;
    }
}