import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        final int INF = 1_000_000_000;

        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int[] item : info) {
            int aCost = item[0];
            int bCost = item[1];

            int[] next = new int[m];
            Arrays.fill(next, INF);

            for (int b = 0; b < m; b++) {
                if (dp[b] == INF) continue;

                // 1) A가 훔치는 경우
                int a2 = dp[b] + aCost;
                if (a2 < n) {
                    next[b] = Math.min(next[b], a2);
                }

                // 2) B가 훔치는 경우
                int b2 = b + bCost;
                if (b2 < m) {
                    next[b2] = Math.min(next[b2], dp[b]);
                }
            }

            dp = next;
        }

        int ans = INF;
        for (int b = 0; b < m; b++) ans = Math.min(ans, dp[b]);

        return ans == INF ? -1 : ans;
    }
}
