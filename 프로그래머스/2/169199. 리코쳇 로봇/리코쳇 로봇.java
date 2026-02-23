import java.util.*;

class Solution {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int sx = -1, sy = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    sx = i; sy = j;
                    break;
                }
            }
            if (sx != -1) break;
        }

        return bfs(board, n, m, sx, sy);
    }

    private int bfs(String[] board, int n, int m, int sx, int sy) {
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (board[x].charAt(y) == 'G') return dist[x][y];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x;
                int ny = y;

                
                while (true) {
                    int tx = nx + dx[dir];
                    int ty = ny + dy[dir];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) break;
                    if (board[tx].charAt(ty) == 'D') break;

                    nx = tx;
                    ny = ty;
                }

                if (dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        return -1;
    }
}