/*
현재 증설된 서버를 확인
*/
import java.io.*;
import java.util.*;
class Solution {
    
    private class Node {
        int endTime;
        int cnt;
        
        Node(int endTime, int cnt) {
            this.endTime = endTime;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        
        
        int result = 0;
        
        int[] output = new int[24];
        
        int player = 0;
        for(int i=0; i<24;i++) {
            if(players[i]!=0) {
                int cnt = players[i] / m;
                if(output[i] < cnt) {
                    int add = cnt - output[i];
                    
                    System.out.println(add+" "+i);
                    
                    int idx = 0;
                    while(idx < k && i+idx < 24) {
                        output[i+idx] += add;
                        idx++;
                    }
                    result += add;
                }
            }
        }
        
        System.out.println(Arrays.toString(output));
        
        int answer = result;
        return answer;
    }
}