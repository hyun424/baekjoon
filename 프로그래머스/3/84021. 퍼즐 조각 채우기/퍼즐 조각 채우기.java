import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        boolean[][] visitedBoard = new boolean[n][n];
        boolean[][] visitedTable = new boolean[n][n];

        List<List<int[]>> emptySpaces = new ArrayList<>();
        List<List<int[]>> blocks = new ArrayList<>();

        // 1. 게임 보드에서 빈 공간(0) 추출
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (!visitedBoard[r][c] && game_board[r][c] == 0) {
                    emptySpaces.add(extractShape(game_board, visitedBoard, r, c, 0));
                }
            }
        }

        // 2. 테이블에서 블록(1) 추출
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (!visitedTable[r][c] && table[r][c] == 1) {
                    blocks.add(extractShape(table, visitedTable, r, c, 1));
                }
            }
        }

        int answer = 0;
        boolean[] used = new boolean[blocks.size()];

        // 3. 빈 공간마다 맞는 블록 찾기
        for (List<int[]> empty : emptySpaces) {
            for (int i = 0; i < blocks.size(); i++) {
                if (used[i]) continue;
                if (empty.size() != blocks.get(i).size()) continue;

                if (canMatch(empty, blocks.get(i))) {
                    used[i] = true;
                    answer += empty.size();
                    break;
                }
            }
        }

        return answer;
    }

    private List<int[]> extractShape(int[][] board, boolean[][] visited, int sr, int sc, int target) {
        int n = board.length;
        Queue<int[]> q = new LinkedList<>();
        List<int[]> shape = new ArrayList<>();

        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            shape.add(new int[]{r, c});

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;
                if (board[nr][nc] != target) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }

        return normalize(shape);
    }

    private List<int[]> normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        for (int[] pos : shape) {
            minR = Math.min(minR, pos[0]);
            minC = Math.min(minC, pos[1]);
        }

        List<int[]> normalized = new ArrayList<>();
        for (int[] pos : shape) {
            normalized.add(new int[]{pos[0] - minR, pos[1] - minC});
        }

        normalized.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        return normalized;
    }

    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();

        for (int[] pos : shape) {
            int r = pos[0];
            int c = pos[1];
            rotated.add(new int[]{c, -r});
        }

        return normalize(rotated);
    }

    private boolean canMatch(List<int[]> empty, List<int[]> block) {
        List<int[]> rotated = block;

        for (int i = 0; i < 4; i++) {
            if (isSame(empty, rotated)) {
                return true;
            }
            rotated = rotate(rotated);
        }

        return false;
    }

    private boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }
}