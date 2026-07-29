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
    public int solution(int n, int[][] vertex) {
        int answer = 0;
        int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){   
            graph.add(new ArrayList<>());
        }
        
        for (int[] v : vertex){
            int a = v[0];
            int b = v[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> (a.cost - b.cost));
        pq.offer(new Node(1, 0));
        while(!pq.isEmpty()){
            Node prev = pq.poll();
            for(int next : graph.get(prev.to)){
                if(prev.cost  > dist[prev.to]) continue;
                int newCost = prev.cost + 1;
                if(newCost < dist[next]){
                    dist[next] = newCost;
                    pq.offer(new Node(next, newCost));
                }
            }
            
            
        }
        
            int max = 0;
            for(int i = 1; i <= n; i++){
                if(dist[i] > max){
                    max = dist[i];
                }
            
            }
            
            for(int i = 1; i <= n; i++){
                if(dist[i] == max){
                    answer++;
                }
            }
        return answer;
    }
}