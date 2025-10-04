import java.io.*;
import java.util.*;

class Solution {
    
    Deque<Integer> dStack = new ArrayDeque<>();
    Deque<Integer> sStack = new ArrayDeque<>();
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for(int i=0; i<n;i++) {
            for(int j=0; j<deliveries[i];j++) {
                dStack.push(i+1);
            }
            
            for(int j=0; j<pickups[i];j++) {
                sStack.push(i+1);
            }
        }
        
        while(!dStack.isEmpty() && !sStack.isEmpty()) {
            int d = dStack.peek();
            int s = sStack.peek();
            
            answer += Math.max(d, s) * 2L;
            
            for(int i=0; i<cap;i++) {
                if(!dStack.isEmpty()) dStack.pop();
                if(!sStack.isEmpty()) sStack.pop();
            }
        }
        
        while(!dStack.isEmpty()) {
            int d = dStack.peek();
            
            answer += d * 2L;
            
            for(int i=0; i<cap;i++) {
                if(!dStack.isEmpty()) dStack.pop();
            }
        }
        
        while(!sStack.isEmpty()) {
            int s = sStack.peek();
            
            answer += s * 2L;
            
            for(int i=0; i<cap;i++) {
                if(!sStack.isEmpty()) sStack.pop();
            }
        }
        
        return answer;
    }
}