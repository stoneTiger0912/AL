import java.io.*;
import java.util.*;

class Solution {
    private int[][] graph;
    
    private int[][] rotate(int[][] key, int time, int KR, int KC) {
        int[][] rotated;
        
        switch(time) {
            case 0:
                rotated = new int[KC][KR];
                
                for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[(KR-1)-r][c];
                    }
                }
                return rotated;
            case 1:
                rotated = new int[KR][KC];
                
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[(KR-1) - r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 2:
                rotated = new int[KC][KR];
                
               for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 3:
                rotated = new int[KR][KC];
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[r][c];
                    }
                }
                return rotated;
        }
        
        return null;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        //키의 크기
        int KR = key.length;
        int KC = key[0].length;
        
        //자물쇠의 크기
        int LR = lock.length;
        int LC = lock[0].length;
        
        //키의 가로 세로 중 큰 것을 구함
        int add = Math.max(KR, KC);
        
        //lock을 확장
        int R = LR + ((add-1) * 2);
        int C = LC + ((add-1) * 2);
        
        int[][] graph = new int[R][C];
        
        for(int r=0; r<R;r++) {
            Arrays.fill(graph[r] ,-1);
        }
        
        for(int r=0;r<LR;r++) {
            for(int c=0; c<LC;c++) {
                graph[add+r][add+c] = lock[r][c];
            }
        }
        
        //회전 4번하면되므로 맨처음부터 회전
        for(int t=0; t<4;t++) {
            int[][] curKey;
            
            //90도 회전, 270도 회전인 경우
            if(t%2==0) {
                curKey = new int[KC][KR];
            }
            else {
                curKey = new int[KR][KC];
            }
            
            curKey = rotate(key, t, KR, KC);
            
            // print(curKey);
            
            for(int r=0; r<R;r++) {
                for(int c=0; c<C;c++) {
                    boolean isClear = check(r, c, graph, curKey);
                    if(isClear) return true;
                }
            }
            
        }
        
        return false;
    }
    
    private boolean check(int curR, int curC, int[][] graph, int[][] key) {
        int R = graph.length;
        int C = graph[0].length;
        
        int KR = key.length;
        int KC = key[0].length;
        
        int[][] tmp = new int[R][C];
        for(int r=0; r<R;r++) {
            System.arraycopy(graph[r], 0, tmp[r], 0, C);
        }
        for(int r=0;r<KR;r++) {
            for(int c=0; c<KC;c++) {
                
                if(((curR+r) >= R) || ((curC + c) >= C)) continue;

                if(key[r][c]==1) {
                    if(tmp[curR+r][curC+c]==1) return false;
                    else tmp[curR+r][curC+c] = 1;
                }
            }
        }
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(tmp[r][c]==0) return false;
                
            }
        }
        
        return true;
    }
    
    private void print(int[][] list) {
        for(int[] r: list) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}