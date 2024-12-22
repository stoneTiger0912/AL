import java.io.*;
import java.util.*;

class Solution {
    
    static int maxNum;
    
    static int[] selected, mineralList;
    
    static int[][] scores = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    public void back(int[] picks, int idx) {
        
        if(idx==maxNum) {
            check();
            return;
        }
        
        for(int i=0; i<3;i++) {
            if(picks[i]==0) continue;
            
            selected[idx] = i;
            picks[i]--;
            back(picks, idx+1);
            picks[i]++;
        }
    }
    
    static int res = Integer.MAX_VALUE;
    
    public void check() {
        // System.out.println(Arrays.toString(selected));
        
        boolean flag = false;
        int cnt = 0;
        for(int i=0; i<selected.length;i++) {
            int start = i * 5;
            int end = (i+1) * 5;
            if(end >= mineralList.length) {
                flag = true;
                end = mineralList.length;
            }
            // System.out.println(end);
            for(int s=start;s<end;s++) {
                cnt += scores[selected[i]][mineralList[s]];
            }
            
            if(flag) break;
        }
        
        res = Math.min(res, cnt);
        
    }
    
    public int solution(int[] picks, String[] minerals) {
        
        maxNum = minerals.length / 5;
        
        if(minerals.length % 5 != 0) {
            maxNum++;
        }
        
        int pickNum = 0;
        for(int i=0; i<3;i++) {
            pickNum += picks[i];
        }
        
        maxNum = Math.min(maxNum, pickNum);
        
        selected = new int[maxNum];
        
        mineralList = new int[minerals.length];
        for(int i=0; i<minerals.length;i++) {
            switch(minerals[i]) {
                case "diamond":
                    mineralList[i] = 0;
                    break;
                case "iron":
                    mineralList[i] = 1;
                    break;
                case "stone":
                    mineralList[i] = 2;
                    break;
            }
        }
        
        back(picks, 0);
        
        System.out.println(res);
        
        
        int answer = res;
        return answer;
    }
}