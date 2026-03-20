import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


public class Main {
    private static int INF = 1_000_000_000;
    private static class node {
        int to;
        int cost;

        node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i  = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new node(b, c));
            graph.get(b).add(new node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int startToV1 = dijkstra(1,v1, graph, N);
        int startToV2 = dijkstra(1,v2, graph, N);
        int v1toV2 = dijkstra(v1,v2, graph, N);
        int v2toV1 = v1toV2;
        int v1toN = dijkstra(v1,N, graph, N);
        int v2toN = dijkstra(v2,N, graph, N);

        long path1 = (long) startToV1 + v1toV2 + v2toN;
        long path2 = (long) startToV2 + v2toV1 + v1toN;

        long answer = Math.min(path1, path2);
        if (answer >= INF) {
            System.out.println("-1");
        }else System.out.println(answer);
        // int n = Integer.parseInt(br.readLine());
    }
    private static int dijkstra(int start, int end, ArrayList<ArrayList<node>> graph, int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new node(start, 0));
        while (!pq.isEmpty()){
            node cur = pq.poll();
            int node = cur.to;
            int cost = cur.cost;

            for (node n : graph.get(node)){
                if (dist[n.to] > n.cost + cost){
                    dist[n.to] = n.cost + cost;
                    pq.offer(new node(n.to, dist[n.to]));
                }

            }
        }
        return dist[end];
    }
}
