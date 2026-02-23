import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            List<Integer>[] graph = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
            }

            // i번째 간선 제외하고 그래프 구성
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;

                int a = wires[j][0];
                int b = wires[j][1];

                graph[a].add(b);
                graph[b].add(a);
            }

            // BFS
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> q = new ArrayDeque<>();
            q.add(1);
            visited[1] = true;

            int count = 1;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        count++;
                    }
                }
            }

            int diff = Math.abs((n - count) - count);
            answer = Math.min(answer, diff);
        }

        return answer;
    }
}