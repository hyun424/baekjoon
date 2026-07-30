import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] win = new boolean[n + 1][n + 1];
        
        for(int[] result : results){
            int a = result[0];
            int b = result[1];
       
            win[a][b] = true;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                for (int k = 1; k <= n; k++){
                    if(win[j][i] && win[i][k]){
                        win[j][k] = true;
                    }
                }
            }
        }
        for(int i = 1; i <= n; i++){
            int knownCount = 0;
            for (int j = 1; j <= n; j++){
                if(i == j){
                    continue;
                }
                if(win[i][j] || win[j][i]){
                    knownCount++;
                }
                if(knownCount == n - 1){
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}
/*
i j j k 이면 i k 가 성립한다.
*/