import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] ans = new int[5];

        for (int k = 0; k < 5; k++) {
            String[] room = places[k];
            boolean ok = true;

            for (int r = 0; r < 5 && ok; r++) {
                for (int c = 0; c < 5 && ok; c++) {
                    if (room[r].charAt(c) == 'P') {
                        if (violate(room, r, c)) ok = false;
                    }
                }
            }
            ans[k] = ok ? 1 : 0;
        }
        return ans;
    }

    private boolean violate(String[] room, int r, int c) {
        // 1) 맨해튼 거리 1 (상하좌우)
        int[] dr1 = {-1, 1, 0, 0};
        int[] dc1 = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr1[i];
            int nc = c + dc1[i];
            if (in(nr, nc) && room[nr].charAt(nc) == 'P') return true;
        }

        // 2) 맨해튼 거리 2 - 직선 (중간이 O면 위반)
        // 위/아래
        if (in(r - 2, c) && room[r - 2].charAt(c) == 'P' && room[r - 1].charAt(c) == 'O') return true;
        if (in(r + 2, c) && room[r + 2].charAt(c) == 'P' && room[r + 1].charAt(c) == 'O') return true;
        // 좌/우
        if (in(r, c - 2) && room[r].charAt(c - 2) == 'P' && room[r].charAt(c - 1) == 'O') return true;
        if (in(r, c + 2) && room[r].charAt(c + 2) == 'P' && room[r].charAt(c + 1) == 'O') return true;

        // 3) 맨해튼 거리 2 - 대각선
        // (r±1, c±1)에 P가 있으면, 사이의 두 칸이 모두 X여야 안전
        int[] dr2 = {-1, -1, 1, 1};
        int[] dc2 = {-1, 1, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr2[i];
            int nc = c + dc2[i];
            if (!in(nr, nc)) continue;

            if (room[nr].charAt(nc) == 'P') {
                // 사이 칸: (r, nc)와 (nr, c)
                if (room[r].charAt(nc) == 'O' || room[nr].charAt(c) == 'O') return true;
            }
        }

        return false;
    }

    private boolean in(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }
}