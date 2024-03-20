class Solution {
    public int solution(int[][] board, int[][] skill) {
        int R = board.length;
        int C = board[0].length;
        
        int[][] dp = new int[R+2][C+2];
        
        int result = 0;
        for(int i=0; i<skill.length;i++) {
            boolean isDestory = skill[i][0] == 1 ? true:false;
            int r1 = skill[i][1];
            int r2 = skill[i][3];
            
            int c1 = skill[i][2];
            int c2 = skill[i][4];
            
            int degree = 0;
            
            if(isDestory) degree = -skill[i][5];
            else degree = skill[i][5];
            //1칸씩 테두리 하기
            dp[r1+1][c1+1] += degree;
            dp[r2+2][c2+2] += degree;
            
            dp[r2+2][c1+1] += -degree;
            dp[r1+1][c2+2] += -degree;
        }
        
        for(int r=1;r<=R;r++) {
            for(int c=1;c<=C;c++) {
                dp[r][c] += (dp[r-1][c]+dp[r][c-1]-dp[r-1][c-1]);
            }
        }
        
        for(int r=0; r<R;r++) {
            for(int c=0;c<C;c++) {
                if(board[r][c]+dp[r+1][c+1] >= 1) result++;
            }
        }
        return result;
    }
}