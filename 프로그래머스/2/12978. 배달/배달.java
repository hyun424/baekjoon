import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<List<int[]>> graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 그래프 구성
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> o1[1] - o2[1]
        );
        
        pq.offer(new int[]{1, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int cost = cur[1];
            
            if (cost > dist[now]) continue;
            
            for (int[] next : graph.get(now)) {
                int nextNode = next[0];
                int nextCost = cost + next[1];
                
                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }
        
        return answer;
    }
}