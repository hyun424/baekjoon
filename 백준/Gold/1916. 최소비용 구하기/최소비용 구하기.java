import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Main {
    private static final Long INF = Long.MAX_VALUE;
    private static class Node{
        int to;
        long cost;
        Node(int to, long cost){
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        long answer = dijkstra(start, end, graph);
        System.out.println(answer);


        // int n = Integer.parseInt(br.readLine());
    }
    private static long dijkstra(int start, int end, ArrayList<ArrayList<Node>> graph){
        long[] dist = new long[graph.size()];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        pq.offer(new Node(start,0));
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if (cur.cost > dist[cur.to]) continue;
            for(Node next : graph.get(cur.to)){
                if (dist[next.to] > dist[cur.to] + next.cost){
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist[end];
    }
}
