class Solution {

    static boolean[] visited;
    static int max = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return max;
    }

    private void dfs(int fatigue, int[][] dungeons, int count) {

        max = Math.max(max, count);

        for (int i = 0; i < dungeons.length; i++) {

            // 아직 방문 안했고, 최소 필요 피로도 충족하면
            if (!visited[i] && fatigue >= dungeons[i][0]) {

                visited[i] = true;
                dfs(fatigue - dungeons[i][1], dungeons, count + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }
}