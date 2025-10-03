import java.io.*;
import java.util.*;

class Solution {
    
    private class Time {
        int year;
        int month;
        int day;
        
        Time(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
        
        boolean isDelete(Time time) {
            int cur = this.year * (12*28) + this.month * 28 + this.day;
            int compareDate = time.year * (12*28) + time.month * 28 + time.day;
            
            if(cur>=compareDate) {
                return true;
            }
            
            return false;
        }
        
        void addTime(int month) {
            this.month += month;
        }
    }
    
    private Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        System.out.println(today);
        Time itoday = changeTime(today);
        
        for(int i=0; i<terms.length;i++) {
            String[] tmp = terms[i].split(" ");
            map.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<privacies.length;i++) {
            String[] tmp = privacies[i].split(" ");
            
            Time date = changeTime(tmp[0]);
            
            date.addTime(map.get(tmp[1]));
            
            if(itoday.isDelete(date)) {
                list.add(i+1);
            }
            
        }
        
        int[] answer = list.stream().mapToInt(a->a).toArray();
        
        return answer;
    }
    
    private Time changeTime(String today) {
        String[] tmp = today.split("\\.");
        
        System.out.println(Arrays.toString(tmp));
        
        Time time = new Time(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
                                              
        // return null;                          
        return time;
    }
}