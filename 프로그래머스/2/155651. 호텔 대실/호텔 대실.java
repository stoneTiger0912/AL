import java.io.*;
import java.util.*;

class Solution {
    
    private class Node implements Comparable<Node> {
        int start;
        int end;
        
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Node n) {
            return Integer.compare(this.start, n.start);
        }
    }
    
    public int solution(String[][] book_time) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int answer = 0;
        
        for(int i=0; i<book_time.length;i++) {
            String[] startList = book_time[i][0].split(":");
            String[] endList = book_time[i][1].split(":");
            
            int start = Integer.parseInt(startList[0]) * 60 + Integer.parseInt(startList[1]);
            int end = Integer.parseInt(endList[0]) * 60 + Integer.parseInt(endList[1]);
            
            pq.offer(new Node(start, end));
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        
        int result = 0;
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            
            int size = queue.size();
            while(--size>= 0) {
                Node qNode = queue.poll();
                if(qNode.end + 10 > n.start) queue.offer(qNode);
            }
            
            queue.offer(n);
            
            answer = Math.max(answer, queue.size());
            
        }
        
        
        return answer;
    }
}