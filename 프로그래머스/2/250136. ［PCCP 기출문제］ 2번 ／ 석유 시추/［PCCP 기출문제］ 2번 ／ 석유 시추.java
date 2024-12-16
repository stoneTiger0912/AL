import java.io.*;
import java.util.*;
/*
BFS로 석유가 있는 곳들을 찾고 배열에 저장
N^2 -> 250_000
set[]을 하나 만들어서 각 열에 어떤 석유 위치가 있는지를 넣음

*/
class Solution {
    
    static int R, C;
    static Set<Integer>[] setList;
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int BFS(int[][] land, int startR, int startC, int idx) {
        Queue<Node> queue = new ArrayDeque<>();
        
        queue.offer(new Node(startR, startC));
        land[startR][startC] = 0;
        setList[startC].add(idx);
        int cnt = 1;
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            
            for(int d = 0; d<4;d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || land[nr][nc] == 0) continue;
                
                land[nr][nc] = 0;
                setList[nc].add(idx);
                cnt++;
                queue.offer(new Node(nr, nc));
                
            }
            
        }
        
        return cnt;
    }
    
    public int solution(int[][] land) {
        
        R = land.length;
        C = land[0].length;
        
        setList = new Set[C];
        for(int c=0; c<C;c++) {
            setList[c] = new HashSet<>();
        }
        
        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                //석유인 경우 BFS돌림
                if(land[r][c]==1) {
                    int res = BFS(land, r, c, idx);
                    map.put(idx++, res);
                }
            }
        }
        
        int answer = 0;
        
        for(int c=0; c<C;c++) {
            int tmp = 0;
            for(Integer i:setList[c]) {
                tmp += map.get(i);
            }
            answer = Math.max(answer, tmp);
        }
        
        return answer;
    }
}