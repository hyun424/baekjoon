import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 1) 인접 리스트 준비
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        // 2) 간선 추가 (양방향)
        for (int[] e : edge) {
            int a = e[0], b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // 3) BFS 최단거리(간선 수) 구하기
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[1] = 0;
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : graph[cur]) {
                if (dist[nxt] != -1) continue;
                dist[nxt] = dist[cur] + 1;
                q.offer(nxt);
            }
        }

        // 4) 최대 거리와 그 개수
        int max = 0;
        for (int i = 1; i <= n; i++) max = Math.max(max, dist[i]);

        int ans = 0;
        for (int i = 1; i <= n; i++) if (dist[i] == max) ans++;

        return ans;
    }
}