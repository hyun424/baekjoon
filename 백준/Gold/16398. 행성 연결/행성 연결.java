import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[n];
        int[] minEdge = new int[n];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0;

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int cur = -1;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && minEdge[j] < min) {
                    min = minEdge[j];
                    cur = j;
                }
            }

            visited[cur] = true;
            answer += min;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && cost[cur][j] < minEdge[j]) {
                    minEdge[j] = cost[cur][j];
                }
            }
        }

        System.out.println(answer);
    }
}
