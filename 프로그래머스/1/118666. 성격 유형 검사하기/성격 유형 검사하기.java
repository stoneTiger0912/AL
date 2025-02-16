import java.io.*;
import java.util.*;
class Solution {
    
    private Map<Character, Integer> map = new HashMap<>();
    
    private void init() {
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
    }
    
    private char check(char a, char b) {
        int aCnt = map.get(a);
        int bCnt = map.get(b);
        
        if(aCnt > bCnt) return a;
        else if(aCnt < bCnt) return b;
        else {
            return a > b ? b : a;
        }
        
    }
    
    public String solution(String[] survey, int[] choices) {
        
        init();
        
        String answer = "";
        int len = survey.length;
        
        for(int i=0; i<len;i++) {
            String q = survey[i];
            int a = choices[i] - 4;
            
            if(a > 0) {
                char c = q.charAt(1);
                
                map.put(c, map.get(c)+a);
            }
            else if(a < 0) {
                char c = q.charAt(0);
                map.put(c, map.get(c)-a);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(check('R', 'T'));
        sb.append(check('C', 'F'));
        sb.append(check('J', 'M'));
        sb.append(check('A', 'N'));
        answer = sb.toString();
        
        return answer;
    }
}