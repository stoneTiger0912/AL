/*
부분집합
26C13

*/
import java.io.*;
import java.util.*;
class Solution {
    
    private final int alphabet = 26;
    
    private boolean[][] orderList;
    
    private class Node implements Comparable<Node> {
        String str;
        int cnt;
        
        Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
        
        public int compareTo(Node o) {
            return o.cnt - this.cnt;
        }
    }
    
    private PriorityQueue<Node> pq = new PriorityQueue<>();
    
    private boolean[] selected = new boolean[alphabet]; 
    
    private void back(int idx, int cnt, int max) {        
        if(idx==alphabet || cnt==max) {
            if(idx==alphabet) {
                if(cnt < max) return;
            }
            int flag = check();
            if(flag >=2) {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<alphabet;i++) {
                    if(selected[i]) {
                        String tmp = Character.toString('A'+i);
                        sb.append(tmp);
                    }
                }
                pq.offer(new Node(sb.toString(), flag));
            }
            return;
        }
        selected[idx] = true;
        back(idx+1, cnt+1, max);
        
        selected[idx] = false;
        back(idx+1, cnt, max);
    }
    
    private int check() {
        int len = orderList.length;
        int cnt = 0;
        A: for(int i=0; i<len;i++) {
            for(int j=0; j<alphabet;j++) {
                if(selected[j]) {
                    if(!orderList[i][j]) continue A;
                } 
            }
            cnt++;
        }
        return cnt;
    }
    
    public String[] solution(String[] orders, int[] course) {
        
        orderList = new boolean[orders.length][alphabet];
        
        for(int i=0; i<orders.length;i++) {
            String order = orders[i];
            for(int j=0; j<order.length();j++) {
                int idx = order.charAt(j) - 'A';
                orderList[i][idx] = true;
            }
        }
        
        List<String> list = new ArrayList<>();
        
        for(int i:course) {
            back(0, 0, i);
            int cnt = 0;
            while(!pq.isEmpty()) {
                Node n = pq.poll();
                if(n.cnt >=cnt) {
                    list.add(n.str);
                    cnt = n.cnt;
                }
            }
        }
        
        list.sort(String::compareTo);
        
        String[] answer = new String[list.size()];
        int idx = 0;
        for(String str: list) {
            answer[idx++] = str;
        }

        return answer;
    }
}