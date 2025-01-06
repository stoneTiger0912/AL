import java.util.*;
import java.io.*;

class Solution {
    
    private HashMap<String, List<String>> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        int len = clothes.length;
        
        for(int i=0; i<len;i++) {
            String key = clothes[i][1];
            String value = clothes[i][0];
            
            if(map.containsKey(key)) {
                map.get(key).add(value);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(value);
                map.put(key, list);
            }
            
        }
        
        Set<String> keyList = map.keySet();
    
        int answer = 1;
        
        for(String k: keyList) {
            answer *= map.get(k).size()+1;
        }
        answer--;
        return answer;
    }
}