import java.util.*;

class Solution {

    static class Edge {
        int to;
        int w;
        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 1) 그래프 구성 (인접 리스트)
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] p : paths) {
            int a = p[0], b = p[1], w = p[2];
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        // 2) gate / summit 빠른 판별용 boolean
        boolean[] isGate = new boolean[n + 1];
        for (int g : gates) isGate[g] = true;

        boolean[] isSummit = new boolean[n + 1];
        for (int s : summits) isSummit[s] = true;

        // summits는 "intensity 동률이면 번호 작은 summit" 요구 때문에 정렬
        Arrays.sort(summits);

        // 3) minimax dijkstra (multi-source)
        final int INF = Integer.MAX_VALUE;
        int[] inten = new int[n + 1];
        Arrays.fill(inten, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        for (int g : gates) {
            inten[g] = 0;
            pq.offer(new int[]{g, 0}); // {node, intensity}
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if (cost > inten[node]) continue;

            // summit이면 더 진행하지 않음 (summit을 중간 경유로 쓰는 것 방지)
            if (isSummit[node]) continue;

            for (Edge e : graph[node]) {
                int next = e.to;

                // "시작/끝을 제외한 출입구 방문 금지" 구현:
                // 탐색 중에는 gate로 들어가서 확장하는 걸 금지(=중간에 gate 방문 차단)
                if (isGate[next]) continue;

                int nextCost = Math.max(cost, e.w);

                if (nextCost < inten[next]) {
                    inten[next] = nextCost;
                    pq.offer(new int[]{next, nextCost});
                }
            }
        }

        // 4) 답 선택: summit 중 intensity 최소, 동률이면 번호 작은 summit
        int bestSummit = -1;
        int bestIntensity = INF;

        for (int s : summits) {
            if (inten[s] < bestIntensity) {
                bestIntensity = inten[s];
                bestSummit = s;
            }
        }

        return new int[]{bestSummit, bestIntensity};
    }
}