import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int h = park.length;
        int w = park[0].length;

        int[][] dp = new int[h][w];
        int maxSquare = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!park[i][j].equals("-1")) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                    Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }

                if (dp[i][j] > maxSquare) maxSquare = dp[i][j];
            }
        }

        int answer = -1;
        for (int m : mats) {
            if (m <= maxSquare) answer = Math.max(answer, m);
        }
        return answer;
    }
}
