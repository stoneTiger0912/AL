import java.io.*;
import java.util.*;

class Solution {
    
    private class Node implements Comparable<Node> {
        String head;
        int number;
        int idx;
        
        Node(String head, int number, int idx) {
            this.head = head;
            this.number = number;
            this.idx = idx;
        }
        
        public int compareTo(Node n) {
            if(this.head.compareTo(n.head) > 0) {
                return 1;
            }
            else if(this.head.compareTo(n.head) < 0) {
                return -1;
            }
            else {
                if(Integer.compare(this.number, n.number)>0) {
                    return 1;
                }
                else if(Integer.compare(this.number, n.number)<0) {
                    return -1;
                }
                else {
                    return Integer.compare(this.idx, n.idx);
                }
                
            }
        }
    }
    
    public String[] solution(String[] files) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i=0; i<files.length;i++) {
            
            String head = "";
            int number = 0;
            String tail = "";
            
            int idx = 0;
            while(idx <= files[i].length()) {
                if(files[i].charAt(idx)>='0' && files[i].charAt(idx)<='9') break;
                idx++;
            }
            
            head = files[i].substring(0, idx).toLowerCase();
            
            int mid = idx;
            while(idx < files[i].length()) {
                if(files[i].charAt(idx)>='0' && files[i].charAt(idx)<='9') idx++;
                else break;
            }
            
            number = Integer.parseInt(files[i].substring(mid, idx));
            // number = files[i].substring(mid, idx);
            
            
            // System.out.println(head);
            // System.out.println(number);
            // System.out.println();
            
            pq.offer(new Node(head, number, i));
        }
        
        String[] answer = new String[files.length];
        
        int idx = 0;
        
        while(!pq.isEmpty()) {
            
            Node n = pq.poll();
            
            String result = files[n.idx];
            
            answer[idx++] = result;
        }
        
        return answer;
    }
}