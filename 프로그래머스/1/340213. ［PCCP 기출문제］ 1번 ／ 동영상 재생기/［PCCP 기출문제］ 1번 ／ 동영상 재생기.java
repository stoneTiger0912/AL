import java.util.*;

class Solution {
    public class Time implements Comparable<Time>{
        public int min; 
        public int sec;
        
        Time(String mmss){
            String[] splited = mmss.split(":");
            this.min = Integer.parseInt(splited[0]);
            this.sec = Integer.parseInt(splited[1]);
        }
        
        public void adjustTime(int sec, Time end){
            int totalSec = this.min * 60 + this.sec + sec;
            int endSec = end.min * 60 + end.sec;
            
            if(totalSec < 0){
                totalSec = 0;
            }else if(totalSec >= endSec){
                totalSec = endSec;
            }
            
            this.min = totalSec / 60;
            this.sec = totalSec % 60;
        }
        
        public void autoMove(Time end, Time opStart, Time opEnd){
            if(this.compareTo(opStart) >= 0 && this.compareTo(opEnd) <= 0){
                this.min = opEnd.min;
                this.sec = opEnd.sec;
            }
        }
        
        public String getTime(){
            return String.format("%02d", this.min) +":" +String.format("%02d",this.sec);
        }
        
        @Override
        public int compareTo(Time other){
            return this.min == other.min ? this.sec - other.sec : this.min - other.min;
        }

    }
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        Time end = new Time(video_len);
        Time now = new Time(pos);
        Time opStart = new Time(op_start);
        Time opEnd = new Time(op_end);
        
        now.autoMove(end, opStart, opEnd);
        for(String cmd : commands){
            if(cmd.equals("next")){
                now.adjustTime(10, end);
            }else if(cmd.equals("prev")){
                now.adjustTime(-10, end);
            }
            now.autoMove(end, opStart, opEnd);
        }
        
        String answer = now.getTime();
        return answer;
    }
}