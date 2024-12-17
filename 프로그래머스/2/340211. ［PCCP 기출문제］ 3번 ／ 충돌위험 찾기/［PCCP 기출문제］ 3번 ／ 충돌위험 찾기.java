/*
point가 존재하고 각 포인트에서 포인트 사이 최단 경로를 구함
-> 최단 경로가 같으면 r을 우선 적용
map의 크기는 101x101
-> 크기 세기 편하게 하기 위해
routes대로 DFS? -> 시간이 오래 걸릴듯
1번 로봇이 4->2로 가는 최단 경로를 구함
이때 최단 경로이므로 각각의 좌표에 대해
3, 2 -> 4, 7로 간다고 했을 때
r로 +1칸 c로 +5칸 가는것이 조건에 맞게 가는 형태임
리스트로 로봇의 개수 만큼 구한다음 시간 순서에 맞게 저장 후 비교하면 될 듯
*/
import java.util.*;
import java.io.*;
class Solution {
    
    static List<Node>[] list;
    static final int N = 101;
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        Node(int[] position) {
            this.r = position[0];
            this.c = position[1];
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        
        int robotSize = routes.length;
        int routeSize = routes[0].length;
        
        list = new List[robotSize];
        for(int i=0; i<robotSize;i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<robotSize;i++) {
            //1부터 시작이유는 이전값을 가져오면 되는데 최소 좌표가 2개 이므로
            int time = 0;
            for(int j=1; j<routeSize;j++) {
                int preIdx = routes[i][j-1] - 1;
                int curIdx = routes[i][j] - 1;
                int[] pre = {points[preIdx][0], points[preIdx][1]};
                int[] cur = {points[curIdx][0], points[curIdx][1]};

                
                //pre랑 cur의 r을 먼저 비교
                if(pre[0] > cur[0]) {
                    while(pre[0]!=cur[0]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[0]--;
                    }
                }
                else if(pre[0] < cur[0]) {
                    while(pre[0]!=cur[0]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[0]++;
                    }
                }
                
                if(pre[1] > cur[1]) {
                    while(pre[1]!=cur[1]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[1]--;
                    }
                }
                else if(pre[1] < cur[1]) {
                    while(pre[1]!=cur[1]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[1]++;
                    }
                }
                
                if(j==routeSize-1) {     
                    list[i].add(new Node(pre[0], pre[1]));
                }
            }
        }
        
        // for(int i=0; i<robotSize;i++) {
        //     for(Node n: list[i]) {
        //         System.out.println(n.r+" "+n.c);
        //     }
        //     System.out.println();
        // }
        int answer = 0;
        int time = 0;
        for(int i=0; i<robotSize;i++) {
            time = Math.max(time, list[i].size());
        }
        
        Map<Integer, Integer> map;
        
        for(int t=0; t<time;t++) {
            map = new HashMap<>();
            
            for(int i=0; i<robotSize;i++) {
                if(list[i].size()-1 >=t) {
                    int tmp = list[i].get(t).r * 101 + list[i].get(t).c;
                    if(map.containsKey(tmp)) {
                        map.put(tmp, map.get(tmp)+1);
                    }
                    else {
                        map.put(tmp, 1);
                    }
                }
                
            }
            for(Integer key: map.keySet()) {
                if(map.get(key)>1) {
                    answer += 1;
                }
            }
        }
        
        
        return answer;
    }

}