import java.util.*;
import java.io.*;


class Solution {
    
    public int[] solution(int n, long k) {

        k--;
    
        long[] arr = new long[n+1];
        for(int i=1; i<=n;i++) {
            if(i==1) arr[1] = 1;
            else arr[i] = arr[i-1] * i;
        }
        // System.out.println(Arrays.toString(arr));
        
        long[] dp = new long[n];
        
        for(int i=n-1; i>0;i--) {
            long tmp = k / arr[i];
            dp[i] = tmp;
            k %= arr[i];
        }
        // System.out.println(Arrays.toString(dp));
        
        boolean[] visited = new boolean[n+2];
        long[] result = new long[n];
        for(int i=n-1; i>0;i--) {
            int cnt = 0;
            int tmp = 0;
            while(cnt <= dp[i]) {
                if(!visited[tmp+1]) {
                    tmp++;
                    cnt++;
                }
                else tmp++;
            }
            visited[tmp] = true;
            result[i] = tmp;
        }
        for(int i=1; i<=n;i++) {
            if(!visited[i]) {
                result[0] = i;
            }
        }
        
        // System.out.println(Arrays.toString(result));

        int[] answer = new int[n];
        
        for(int i=0; i<n;i++) {
            answer[i] = (int) result[n-i-1];
        }
        
        
        return answer;
    }
}