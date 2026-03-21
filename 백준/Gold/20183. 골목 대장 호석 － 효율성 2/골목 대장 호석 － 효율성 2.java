import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final long INF = Long.MAX_VALUE / 4;

    private static class Node {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        long budget = Long.parseLong(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        long maxEdge = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
            maxEdge = Math.max(maxEdge, cost);
        }

        if (!canReach(start, end, budget, maxEdge, graph, n)) {
            System.out.println(-1);
            return;
        }

        long left = 0;
        long right = maxEdge;
        long answer = maxEdge;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canReach(start, end, budget, mid, graph, n)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canReach(int start, int end, long budget, long limit,
                                    ArrayList<ArrayList<Node>> graph, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.to]) {
                continue;
            }

            if (cur.to == end) {
                return true;
            }

            for (Node next : graph.get(cur.to)) {
                if (next.cost > limit) {
                    continue;
                }

                long nextCost = cur.cost + next.cost;
                if (nextCost > budget || nextCost >= dist[next.to]) {
                    continue;
                }

                dist[next.to] = nextCost;
                pq.offer(new Node(next.to, nextCost));
            }
        }

        return dist[end] <= budget;
    }
}
