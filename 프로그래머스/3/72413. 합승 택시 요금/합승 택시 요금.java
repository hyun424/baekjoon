import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        //따로올때, 같이 갈 때 구분해야하는데 어떻게 구분하지 
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        int[] distS = dijkstra(graph, n, s);
        int[] distA = dijkstra(graph, n, a);
        int[] distB = dijkstra(graph, n, b);

        int answer = Integer.MAX_VALUE;

        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer,
                    distS[k] + distA[k] + distB[k]);
        }
        
        return answer;
    }
    private int[] dijkstra(List<int[]>[] graph, int n, int start){
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = 
            new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        pq.offer(new int[] {start, 0});
        dist[start] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if (cost > dist[node]) continue;
            
            for (int[] next : graph[node]){
                int nextNode = next[0];
                int weight = next[1];
                
                if(dist[nextNode] > cost + weight){
                    dist[nextNode] = cost + weight;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                    
                }
            }
        }
        
        return dist;
    }
}