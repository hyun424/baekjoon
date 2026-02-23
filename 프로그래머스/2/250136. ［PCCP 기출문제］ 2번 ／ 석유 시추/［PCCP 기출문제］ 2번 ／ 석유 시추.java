import java.util.*;

class Solution {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land) {

        int n = land.length;
        int m = land[0].length;

        boolean[][] visited = new boolean[n][m];
        int[][] comp = new int[n][m];

        Map<Integer, Integer> size = new HashMap<>();
        int id = 1;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (land[i][j] == 1 && !visited[i][j]) {

                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    comp[i][j] = id;

                    int cnt = 1;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                                continue;

                            if (land[nx][ny] == 1 && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                comp[nx][ny] = id;
                                q.offer(new int[]{nx, ny});
                                cnt++;
                            }
                        }
                    }

                    size.put(id, cnt);
                    id++;
                }
            }
        }


        int answer = 0;

        for (int col = 0; col < m; col++) {

            Set<Integer> set = new HashSet<>();
            int sum = 0;

            for (int row = 0; row < n; row++) {
                if (comp[row][col] != 0) {
                    set.add(comp[row][col]);
                }
            }

            for (int compId : set) {
                sum += size.get(compId);
            }

            answer = Math.max(answer, sum);
        }

        return answer;
    }
}