import java.util.*;

class Solution {
    private int[] apeach;
    private int[] best;
    private int bestDiff;

    public int[] solution(int n, int[] info) {
        this.apeach = info;
        this.best = null;
        this.bestDiff = 0;

        int[] cur = new int[11];
        dfs(0, n, cur);

        return (best == null) ? new int[]{-1} : best;
    }

    // idx: 0..9 (10점~1점 결정), idx==10이면 0점에 남은 화살 몰아넣고 평가
    private void dfs(int idx, int left, int[] cur) {
        if (idx == 10) {
            cur[10] += left;         // 남은 화살 전부 0점
            evaluate(cur);
            cur[10] -= left;         // 복원
            return;
        }

        // 1) 이 점수를 이기기: apeach[idx] + 1발
        int need = apeach[idx] + 1;
        if (left >= need) {
            cur[idx] = need;
            dfs(idx + 1, left - need, cur);
            cur[idx] = 0;            // 복원
        }

        // 2) 포기: 0발
        dfs(idx + 1, left, cur);
    }

    private void evaluate(int[] cur) {
        int rScore = 0;
        int aScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (cur[i] == 0 && apeach[i] == 0) continue;

            if (cur[i] > apeach[i]) rScore += score;
            else aScore += score; // 같으면 어피치가 가져감
        }

        int diff = rScore - aScore;
        if (diff <= 0) return; // 이기지 못하면 후보 아님

        if (diff > bestDiff) {
            bestDiff = diff;
            best = Arrays.copyOf(cur, 11);
        } else if (diff == bestDiff) {
            // tie-break: 낮은 점수(0점부터) 더 많이 맞힌 배열 우선
            if (best == null || isBetter(cur, best)) {
                best = Arrays.copyOf(cur, 11);
            }
        }
    }

    // true if cand is better than base by tie-break rule
    private boolean isBetter(int[] cand, int[] base) {
        for (int i = 10; i >= 0; i--) { // 0점부터 비교
            if (cand[i] != base[i]) return cand[i] > base[i];
        }
        return false;
    }
}