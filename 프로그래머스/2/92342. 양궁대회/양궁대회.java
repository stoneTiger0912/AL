import java.util.*;
import java.io.*;

class Solution {
    
    static final int LENGTH = 11;
    
    private int maxScore = Integer.MIN_VALUE;
    private int[] maxScoreList = new int[LENGTH];
    
    private void back(int n, int idx, int[] list, int[] info) {
        
        if(n==idx) {
            check(list, info);
            return;
        }
        
        for(int i=0; i<LENGTH;i++) {
            if(list[i]>info[i]) break;
            
            list[i] += 1;
            back(n, idx+1, list, info);
            list[i] -= 1;
        }
    }
    
    private void check(int[] RList, int[] AList) {
        int score = 0;
        for(int i=0; i<LENGTH;i++) {
            if(AList[i]==0 && RList[i]==0) continue;
            else if(RList[i] > AList[i]) score += LENGTH - 1 - i;
            //if(RList[i]==0 && AList[i]==0) continue;
            else score -= LENGTH - 1 - i;
        }
        
        if(score <= 0) return;
        
        if(score >= maxScore) {
            maxScore = score;
            System.arraycopy(RList, 0, maxScoreList, 0, LENGTH);
        }
    }
    
    public int[] solution(int n, int[] info) {
        back(n, 0, new int[LENGTH], info);
        
        int[] answer;
        if(maxScore == Integer.MIN_VALUE) {
            
            answer = new int[1];
            answer[0] = -1;
            
            return answer;
        }
        

        return maxScoreList;
    }
}