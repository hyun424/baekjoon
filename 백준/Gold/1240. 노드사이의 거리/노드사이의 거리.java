import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;

    private static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int dist(int cur, int target, int parent, int res) {
        if (cur == target) {
            return res;
        }

        for (Node next : graph.get(cur)) {
            if (next.to == parent) continue;

            int result = dist(next.to, target, cur, res + next.cost);
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(x).add(new Node(y, d));
            graph.get(y).add(new Node(x, d));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];
            sb.append(dist(x, y, 0, 0)).append('\n');
        }
        System.out.print(sb);
    }
}
