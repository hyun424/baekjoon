import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] size;

    static void dfs(int cur, int parent) {
        size[cur] = 1;

        for (int next : graph[cur]) {
            if (next == parent) continue;
            dfs(next, cur);
            size[cur] += size[next];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        size = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(R, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(size[u]).append('\n');
        }

        System.out.print(sb);
    }
}


