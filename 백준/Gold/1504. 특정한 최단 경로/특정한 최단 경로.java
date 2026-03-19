import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int INF = 1_000_000_000;

    private static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(1, graph, N);
        int[] fromV1 = dijkstra(v1, graph, N);
        int[] fromV2 = dijkstra(v2, graph, N);

        long path1 = (long) from1[v1] + fromV1[v2] + fromV2[N];
        long path2 = (long) from1[v2] + fromV2[v1] + fromV1[N];
        long answer = Math.min(path1, path2);

        if (from1[v1] >= INF || fromV1[v2] >= INF || fromV2[N] >= INF) {
            path1 = Long.MAX_VALUE;
        }

        if (from1[v2] >= INF || fromV2[v1] >= INF || fromV1[N] >= INF) {
            path2 = Long.MAX_VALUE;
        }

        answer = Math.min(path1, path2);
        if (answer == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int[] dijkstra(int start, ArrayList<ArrayList<Node>> graph, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int node = cur.to;
            int cost = cur.cost;

            if (dist[node] < cost) {
                continue;
            }

            for (Node next : graph.get(node)) {
                int nextNode = next.to;
                int nextCost = cost + next.cost;

                if (nextCost >= dist[nextNode]) {
                    continue;
                }

                dist[nextNode] = nextCost;
                pq.offer(new Node(nextNode, nextCost));
            }
        }

        return dist;
    }
}
