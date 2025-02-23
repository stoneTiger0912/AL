/*
1/1 -> 0, 0
3/3 -> 2, 2
*/
import java.io.*;
import java.util.*;
class Solution {
    
    private int[][] graph = new int[4][4];
    
    private int result = 121;
    private int A;
    
    private void back(int idx, int n, int m) {
        
        if(idx==9) {
            
            result = Math.min(result, A-n);
            
            return;
        }
        
        int r = idx / 3;
        int c = idx % 3;
        
        int cnt = graph[r][c];
        for(int i=0; i<=cnt;i++) {
            int a = i*(r+1);
            int b = (cnt - i)*(c+1);
            
            if(a >= n || b >= m) continue;
            
            // System.out.println(a+" "+b);
            
            back(idx+1, n-a, m-b);
            
        }
        
    }
    
    public int solution(int[][] info, int n, int m) {
        
        for(int i=0; i<info.length;i++) {
            int r = info[i][0] - 1;
            int c = info[i][1] - 1;
            graph[r][c] += 1;
        }
        A = n;
        back(0, n, m);
        
        int answer = result == 121 ? -1 : result;
        return answer;
    }
}