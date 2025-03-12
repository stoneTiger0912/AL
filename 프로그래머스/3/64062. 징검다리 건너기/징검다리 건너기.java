/*
이진 탐색
*/
import java.io.*;
import java.util.*;
class Solution {
    
    private int MAX = 0;
    
    private int binarySearch(int[] stones, int k) {
        int start = 0;
        int end = 200_000_000;
        
        while(start<=end) {
            int mid = (start + end) / 2;
            
            boolean flag = check(mid, stones, k);
            
            if(!flag) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
                System.out.println(start);
            }
            
        }
        return start;
    }
    
    private boolean check(int mid, int[] stones, int k) {
        
        int cnt = 0;
        for(int i=0; i<stones.length;i++) {
            if(stones[i] - mid <= 0) cnt++;
            else {
                cnt = 0;
            }
            
            if(cnt == k) return false;
        }
        
        return true;
        
    }
    
    public int solution(int[] stones, int k) {
        int answer = binarySearch(stones, k);
        return answer;
    }
}