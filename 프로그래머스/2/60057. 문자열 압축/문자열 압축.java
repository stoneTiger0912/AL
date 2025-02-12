
import java.io.*;
import java.util.*;

class Solution {
    
    private class Node {
        String input;
        int num;
        
        Node(String input) {
            this.input = input;
            this.num = 1;
        }
        
        void plus() {
            this.num += 1;
        }
        
    }
    
    public int solution(String s) {
        
        int len = s.length() / 2;
        
        int answer = Integer.MAX_VALUE;
        
        if(s.length()==1) return 1;
        
        for(int i=1; i<=len;i++) {
            Deque<Node> stack = new ArrayDeque<>();
            
            int length = 0;
            
            for(int j=0; j<s.length();j+= i) {
                String tmp;
                if(j+i <= s.length()) {
                    tmp = s.substring(j, j+i);
                }
                
                else tmp = s.substring(j, s.length());
                
                if(stack.isEmpty()) stack.push(new Node(tmp));
                else {
                    if(stack.peek().input.equals(tmp)) stack.peek().plus();
                    else stack.push(new Node(tmp));
                }
                
                // System.out.println(tmp);
            }
            
            while(!stack.isEmpty()) {
                Node n = stack.pop();
                String tmp;
                if(n.num==1) tmp = n.input;
                else tmp = n.input+n.num;
                
                length += tmp.length();
                
               // System.out.println("num "+ n.num);
            }
            
            // System.out.println(length);
            
            answer = Math.min(length, answer);
            
            System.out.println();
        }
        
        
        return answer;
    }
}