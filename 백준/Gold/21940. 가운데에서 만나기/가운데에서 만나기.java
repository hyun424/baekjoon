import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n + 1][n + 1];
        int INF = 1_000_000_000;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                graph[i][j] = INF;
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], t);
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <=n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(graph[i][k] == INF || graph[k][j] == INF) continue;
                    graph[i][j] =  Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int k = Integer.parseInt(br.readLine());
        int[] friend = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            friend[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> town = new ArrayList<>();
        int best = INF;
        for (int city = 1; city <= n; city++) {
            int maxTime = 0;
            boolean ok = true;

            for (int i = 0; i < k; i++) {
                int f = friend[i];
                if (graph[f][city] == INF || graph[city][f] == INF) {
                    ok = false;
                    break;
                }
                maxTime = Math.max(maxTime, graph[f][city] + graph[city][f]);
            }

            if (!ok) continue;

            if (maxTime < best) {
                best = maxTime;
                town.clear();
                town.add(city);
            } else if (maxTime == best) {
                town.add(city);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < town.size(); i++) {
            sb.append(town.get(i));
            if (i + 1 < town.size()) sb.append(' ');
        }
        System.out.println(sb);
    }
}
