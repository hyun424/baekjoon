import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        // 시작/도착이 막혀있는 경우(일반화)
        if (maps[0][0] == 0 || maps[n - 1][m - 1] == 0) return -1;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        // maps를 거리로 재활용: 시작칸이 1이므로 그대로 거리=1부터 시작
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 벽(0) 또는 이미 방문(거리값이 1보다 큰 값으로 바뀐 곳 포함)
                if (maps[nx][ny] == 0) continue;
                if (maps[nx][ny] != 1) continue;

                // 거리 갱신 + 방문 처리
                maps[nx][ny] = maps[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        int ans = maps[n - 1][m - 1];
        return (ans == 1) ? -1 : ans; // 도착칸이 갱신 안됐으면 -1
    }
}