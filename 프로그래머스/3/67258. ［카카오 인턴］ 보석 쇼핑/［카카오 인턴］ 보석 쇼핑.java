/*
투포인터
*/
import java.io.*;
import java.util.*;

class Solution {
    
    private Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int max = Integer.MAX_VALUE;
        
        Set<String> set = new HashSet<>();
        
        for(String g : gems) {
            set.add(g);
        }
        int size = set.size();
        
        int start = 0;
        int end = 0;
        
        int cnt = 0;
        
        while(start <= end) {
            
            if(end==gems.length) break;
            
            String lastGem = gems[end];
            if(!map.containsKey(lastGem)) {
                map.put(lastGem, 1);
                cnt++;
            }
            else {
                map.put(lastGem, map.get(lastGem)+1);
            }
            
            while(true) {
                String firstGem = gems[start];
                if(map.get(firstGem) > 1) {
                    start++;
                    map.put(firstGem, map.get(firstGem)-1);
                }
                else break;
            }
            
            
            if(cnt==size) {
                if(end-start < max) {
                    answer[0] = start+1;
                    answer[1] = end+1;
                    max = end-start;
                }
            }
            
            end++;
            
        }
        
        // System.out.println(start+" "+end);
        
        
        return answer;
    }
}