import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] graph = new int[M][N];

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1; j < y2; j++){
                for (int k = x1; k < x2; k++){
                    graph[j][k] = 1;
                }
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                if (graph[i][j] == 0){
                    answer.add(bfs(graph, i, j, M, N));
                }
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        for (int area : answer) {
            sb.append(area).append(' ');
        }
        System.out.println(sb);
    }

    private static int bfs(int[][] graph, int x, int y, int M, int N){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        graph[x][y] = 1;
        int count = 1;

        while (!queue.isEmpty()){

            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < M && 0 <= ny && ny < N){
                    if (graph[nx][ny] == 0){
                        count++;
                        graph[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return count;
    }
}
