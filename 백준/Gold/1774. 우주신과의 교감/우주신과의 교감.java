import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dot = new int[n + 1][2];
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dot[i][0] = x;
            dot[i][1] = y;
        }
        double[][] graph = new double[n + 1][n + 1];
        for (int i = 1; i < n; i++){
            for (int j = i + 1; j <= n; j++){
                long dx = (long) dot[i][0] - dot[j][0];
                long dy = (long) dot[i][1] - dot[j][1];
                double cost = Math.sqrt(dx * dx + dy * dy);
                graph[i][j] = cost;
                graph[j][i] = cost;
            }
        }
        for (int i = 0; i < m; i++){
            st  = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 0;
            graph[y][x] = 0;
        }
        boolean[] visited = new boolean[n + 1];
        double[] minEdge = new double[n + 1];
        Arrays.fill(minEdge, Double.MAX_VALUE);
        minEdge[1] = 0;

        double answer = 0.0;
        for(int i = 1; i <= n; i++){
            int cur = -1;
            double min = Double.MAX_VALUE;

            for (int j = 1; j <= n; j++){
                if (!visited[j] && minEdge[j] < min){
                    min = minEdge[j];
                    cur = j;
                }
            }

            visited[cur] = true;
            answer += min;

            for (int j = 1; j <= n; j++){
                if (!visited[j] && graph[cur][j] < minEdge[j]){
                    minEdge[j] = graph[cur][j];
                }
            }
        }
        System.out.printf("%.2f%n", answer);
    }
}
