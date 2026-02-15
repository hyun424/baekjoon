class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0], x = bandage[1], y = bandage[2];
        int maxHp = health;
        int curHp = health;

        int idx = 0;
        int streak = 0;
        int lastTime = attacks[attacks.length - 1][0];

        for (int time = 1; time <= lastTime; time++) {
            // 공격 타이밍이면 회복 없이 맞음
            if (idx < attacks.length && attacks[idx][0] == time) {
                curHp -= attacks[idx][1];
                idx++;
                streak = 0;
                if (curHp <= 0) return -1;
            } else {
                // 회복
                streak++;
                curHp = Math.min(maxHp, curHp + x);

                if (streak == t) {
                    curHp = Math.min(maxHp, curHp + y);
                    streak = 0;
                }
            }
        }

        return curHp;
    }
}
