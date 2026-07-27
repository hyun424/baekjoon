import java.util.*;

class Solution {
    

    
    private class Node{
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList());
        }
        
        for(int[] r : road){
            int a = r[0];
            int b = r[1];
            int cost = r[2];
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }
        PriorityQueue <Node> pq = new PriorityQueue<>((a,b) -> (a.cost - b.cost));
        pq.offer(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node n : graph.get(cur.to)){
                if(cur.cost > dist[cur.to]) continue;
                int newCost = cur.cost + n.cost;
                if(newCost < dist[n.to]){
                    dist[n.to] = newCost;
                    pq.offer(new Node(n.to, newCost));
                }
            }
        }
        
        for(int i = 1; i <= N; i++){
            if (dist[i] <= K){
                answer++;
            }
        }
        return answer;
    }
}
/*

*/