import java.util.*;

class Solution {
    private static int N;
    private static int INF = Integer.MAX_VALUE / 4;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    
    private static class Node{
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    } 
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        N = n;
        for(int i = 0; i <= N + 1; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares){
            int x = fare[0];
            int y = fare[1];
            int c = fare[2];
            graph.get(x).add(new Node(y, c));
            graph.get(y).add(new Node(x, c));
        }
        int[] fromS = dijkstra(s);
        int[] fromA = dijkstra(a);
        int[] fromB = dijkstra(b);
        answer = INF;
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer,
                              fromS[i] + fromA[i] + fromB[i]);
        }
        
        return answer;
    }
    
    private static int[] dijkstra(int start){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue <Node> pq = new PriorityQueue<>((x,y) -> (x.cost - y.cost));
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node prev = pq.poll();
            
            for(Node next : graph.get(prev.to)){
                if(prev.cost > dist[prev.to]) continue;    
                int newCost = prev.cost + next.cost;
                if(newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }
        return dist;
        
        
    }
}