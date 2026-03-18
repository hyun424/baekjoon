import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx ={1, -1, 0, 0, 0, 0};
    static int[] dy ={0, 0, 1, -1 ,0, 0};
    static int[] dz ={0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C ==0) break;
            Character[][][] graph = new Character[L][R][C];
            int[] start = new int[3];
            int[] end = new int[3];
            for (int i = 0; i < L; i++){
                for(int j = 0; j < R; j++){
                    String line = br.readLine();
                    for(int k = 0; k < C; k++){

                        if (line.charAt(k) == 'S'){
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        }

                        if (line.charAt(k) == 'E'){
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;

                        }
                        graph[i][j][k] = line.charAt(k);
                    }
                }

                br.readLine();
            }
            printExit(graph, start, end, L, R, C);
        }

    }
    private static void printExit(Character[][][] graph, int[] start, int[] end, int L, int R, int C){
        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] visited = new int[L][R][C];

        queue.offer(start);
        while (!queue.isEmpty()){
            int[] cur = queue.poll();

            int cx = cur[0];
            int cy = cur[1];
            int cz = cur[2];
            if (Arrays.equals(cur, end)) {
                System.out.printf("Escaped in %d minute(s).\n", visited[cx][cy][cz]);
                return;
            }

            for (int i = 0; i <  6; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nz = cz + dz[i];
                if (0 <= nx && nx < L &&
                        0 <= ny && ny < R &&
                        0 <= nz && nz < C &&
                        graph[nx][ny][nz] != '#' &&
                        visited[nx][ny][nz] == 0){
                    queue.offer(new int[]{nx, ny, nz});
                    visited[nx][ny][nz] += visited[cx][cy][cz] + 1;
                }
            }


        }
        System.out.println("Trapped!");
        return;
    }
}
