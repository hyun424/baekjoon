import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        int[][] next = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (c < dist[a][b]) {
                dist[a][b] = c;
                next[a][b] = b;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == INF) continue;
                    int newCost = dist[i][k] + dist[k][j];
                    if (newCost < dist[i][j]) {
                        dist[i][j] = newCost;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]);
                if (j < n) sb.append(' ');
            }
            sb.append('\n');
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || next[i][j] == 0) {
                    sb.append(0).append('\n');
                    continue;
                }

                List<Integer> path = new ArrayList<>();
                int cur = i;
                path.add(cur);

                while (cur != j) {
                    cur = next[cur][j];
                    path.add(cur);
                }

                sb.append(path.size());
                for (int node : path) {
                    sb.append(' ').append(node);
                }
                sb.append('\n');
            }
        }

        System.out.print(sb);
    }
}
