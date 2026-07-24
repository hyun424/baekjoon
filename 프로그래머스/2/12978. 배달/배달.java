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
        
        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] r : road){
            int a = r[0];
            int b = r[1];
            int cost = r[2];
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Node> pq = 
            new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.offer(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(current.cost > dist[current.to]){
                continue;
            }
            
            for(Node next : graph.get(current.to)){
                int newCost = current.cost + next.cost;
                
                if (newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }
      
        for (int i = 0; i <= N; i++){
            if (dist[i] <= K){
                answer++;
            }
        }
        return answer;
    }
}