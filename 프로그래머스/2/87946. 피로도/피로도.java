class Solution {

    private boolean[] visited;
    private int max;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        max = 0;

        dfs(k, dungeons, 0);

        return max;
    }

    private void dfs(int fatigue, int[][] dungeons, int count) {
        max = Math.max(max, count);

        for (int i = 0; i < dungeons.length; i++) {
            // 아직 방문하지 않았고,
            // 현재 피로도가 최소 필요 피로도 이상인 경우
            if (!visited[i] && fatigue >= dungeons[i][0]) {
                visited[i] = true;

                dfs(
                    fatigue - dungeons[i][1],
                    dungeons,
                    count + 1
                );

                // 다른 순서도 탐색해야 하므로 방문 복구
                visited[i] = false;
            }
        }
    }
}