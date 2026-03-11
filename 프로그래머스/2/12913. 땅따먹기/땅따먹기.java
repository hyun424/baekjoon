class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int[] dp = new int[4];

        for (int i = 0; i < 4; i++) {
            dp[i] = land[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[] next = new int[4];

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    next[j] = Math.max(next[j], dp[k] + land[i][j]);
                }
            }

            dp = next;
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}