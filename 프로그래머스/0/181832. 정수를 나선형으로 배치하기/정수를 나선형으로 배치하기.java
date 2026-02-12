class Solution {
    public int[][] solution(int n) {
        int[][] arr = new int[n][n];

        int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
        int[] dy = {1, 0, -1, 0};

        int x = 0, y = 0;   // 시작 위치
        int dir = 0;        // 현재 방향
        int num = 1;

        for (int i = 0; i < n * n; i++) {
            arr[x][y] = num++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 벗어나거나 이미 값이 채워졌으면 방향 전환
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] != 0) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx;
            y = ny;
        }

        return arr;
    }
}
