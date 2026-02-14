class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int hope = schedules[i];
            int hopeMin = (hope / 100) * 60 + (hope % 100);
            int limit = hopeMin + 10;

            boolean ok = true;

            for (int j = 0; j < 7; j++) {
                int day = (startday + j - 1) % 7 + 1; // 1~7

                // 토(6), 일(7)은 무시
                if (day == 6 || day == 7) continue;

                int real = timelogs[i][j];
                int realMin = (real / 100) * 60 + (real % 100);

                if (realMin > limit) {
                    ok = false;
                    break;
                }
            }

            if (ok) answer++;
        }

        return answer;
    }
}
