import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] item = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int t = Integer.parseInt(st.nextToken());
            item[i] = t;
        }
        int INF = 100_000_000;
        int[][] graph = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], l);
            graph[b][a] = Math.min(graph[b][a], l);
        }
        for(int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(graph[i][k] == INF || graph[k][j] == INF) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j] );
                }
            }
        }
        int answer = 0;
        for(int i = 1; i <= n; i++) {
            int temp = 0;
            for(int j = 1; j <= n; j++) {
                if(graph[i][j] <= m) {
                    temp += item[j];
                }
                answer = Math.max(answer, temp);
            }
        }
    System.out.println(answer);
        // int n = Integer.parseInt(br.readLine());
    }
}
