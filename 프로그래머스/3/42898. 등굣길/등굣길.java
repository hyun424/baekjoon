class Solution {
    public int solution(int m, int n, int[][] puddles) {
        final int MOD = 1_000_000_007;

        int[][] dp = new int[n + 1][m + 1];
        boolean[][] blocked = new boolean[n + 1][m + 1];

        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            blocked[y][x] = true;
        }

        dp[0][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (blocked[y][x]) {
                    continue;
                }

                dp[y][x] = (dp[y - 1][x] + dp[y][x - 1]) % MOD;
            }
        }

        return dp[n][m];
    }
}