import java.io.*;
import java.util.*;

class Solution {
    
    Map<String, Integer> map = new HashMap<>();
    
    private void init() {
        for(char c = 'A'; c<='Z';c++) {
            map.put(String.valueOf(c), map.size()+1);
        }
    }
    
    public int[] solution(String msg) {
        
        init();
        
        // System.out.println(map.get("Z"));
        
        int start = 0;
        int end = 0;
        
        List<Integer> list = new ArrayList<>();
        String sub = "";
        while(end < msg.length()) {
            String tmp = msg.substring(start, end+1);
            
            if(map.containsKey(tmp)) {
                sub = tmp;
                end++;
            }
            else {
                
                map.put(tmp, map.size()+1);
                
                start = end;
                
                list.add(map.get(sub));
            }
            
        }
        
        list.add(map.get(sub));
        int[] answer = list.stream().mapToInt(Integer::new).toArray();
        
        
        
        return answer;
    }
}