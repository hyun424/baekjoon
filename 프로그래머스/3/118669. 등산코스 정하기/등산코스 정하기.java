import java.util.*;

class Solution {

    ArrayList<int[]>[] graph;
    boolean[] gate;
    boolean[] summit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        gate = new boolean[n + 1];
        summit = new boolean[n + 1];

        for (int g : gates) gate[g] = true;
        for (int s : summits) summit[s] = true;

        // 그래프 방향 제한
        for (int[] p : paths) {
            int a = p[0], b = p[1], w = p[2];

            if (gate[a] || summit[b]) {
                graph[a].add(new int[]{b, w});
            } else if (gate[b] || summit[a]) {
                graph[b].add(new int[]{a, w});
            } else {
                graph[a].add(new int[]{b, w});
                graph[b].add(new int[]{a, w});
            }
        }

        int[] dist = dijkstra(n, gates);

        Arrays.sort(summits);

        int min = Integer.MAX_VALUE;
        int target = 0;

        for (int s : summits) {
            if (dist[s] < min) {
                min = dist[s];
                target = s;
            }
        }

        return new int[]{target, min};
    }

    private int[] dijkstra(int n, int[] gates) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int g : gates) {
            dist[g] = 0;
            pq.offer(new int[]{g, 0});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int cost = now[1];

            if (dist[cur] < cost) continue;
            if (summit[cur]) continue;

            for (int[] next : graph[cur]) {
                int nextNode = next[0];
                int weight = next[1];

                int nextCost = Math.max(cost, weight);

                if (dist[nextNode] > nextCost) {
                    dist[nextNode] = nextCost;
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }

        return dist;
    }
}