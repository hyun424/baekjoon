import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, graph[i][j]);
            }
        }

        int ans = 0;

        for (int drop = 0; drop <= maxHeight; drop++) {
            int temp = bfs(graph, drop, n);
            ans = Math.max(ans, temp);
        }

        System.out.println(ans);
    }

    private static int bfs(int[][] graph, int drop, int n) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > drop && !visited[i][j]) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    res++;

                    while (!queue.isEmpty()) {
                        int[] c = queue.poll();
                        int cx = c[0], cy = c[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = cx + dx[k];
                            int ny = cy + dy[k];

                            if (0 <= nx && nx < n && 0 <= ny && ny < n
                                    && !visited[nx][ny]
                                    && graph[nx][ny] > drop) {
                                queue.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}