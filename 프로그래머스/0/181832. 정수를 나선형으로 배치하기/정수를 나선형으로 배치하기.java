class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        
        int r = 0;
        int c = 0;
        int d = 0;
        
        for(int i=0; i<n*n;i++) {
            answer[r][c] = i+1;
            
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr < 0 || nr >=n || nc < 0 || nc >= n || answer[nr][nc]!=0) {
                d = (d+1) % 4;
                r += dr[d];
                c += dc[d];
            }
            else {
                r = nr;
                c = nc;
            }
            
        }
        
        return answer;
    }
}