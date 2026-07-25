class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        // r2 + 1, c2 + 1 위치를 사용하므로 한 칸씩 크게 생성
        int[][] diff = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            // 공격은 음수, 회복은 양수
            int value = type == 1 ? -degree : degree;

            // 직사각형 범위 변화량 표시
            diff[r1][c1] += value;
            diff[r1][c2 + 1] -= value;
            diff[r2 + 1][c1] -= value;
            diff[r2 + 1][c2 + 1] += value;
        }

        // 가로 누적합
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // 세로 누적합
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        int answer = 0;

        // 기존 내구도 + 모든 스킬의 변화량
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + diff[i][j] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}