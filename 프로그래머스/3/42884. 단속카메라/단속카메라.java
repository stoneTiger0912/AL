/*
그냥 완탐할 경우 3억으로 되지 않음
end를 기준으로 정렬 -> 한번은 단속용 카메라를 만나야함
end를 가지고 만나는 차량 찾기
-20     -15
     -18       -13
            -14        -5
                       -5 -3
즉 위에서 보면
*/
import java.util.*;
import java.io.*;

class Solution {
    
    private class Car implements Comparable<Car> {
        int start;
        int end;
        
        Car(int[] car) {
            this.start = car[0];
            this.end = car[1];
        }
        
        public int compareTo(Car c) {
            return Integer.compare(this.end, c.end);
        }
        
        public String toString() {
            return this.start+" "+this.end;
        }
        
    }
    
    public int solution(int[][] routes) {
        
        PriorityQueue<Car> pq = new PriorityQueue<>();
        
        for(int[] route: routes) {
            pq.offer(new Car(route));
        }
        
        int answer = 0;
        int idx = -30_001;
        
        while(!pq.isEmpty()) {
            Car c = pq.poll();
            // System.out.println(c.toString());
            if(c.start<=idx) continue;
            idx = c.end;
            answer++;
        }
        
        return answer;
    }
}