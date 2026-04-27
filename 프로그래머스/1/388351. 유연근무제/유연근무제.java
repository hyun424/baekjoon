class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int day = startday;
            int limit = addTenMinutes(schedules[i]);

            for (int j = 0; j < 7; j++) {
                // 평일만 체크
                if (day != 6 && day != 7) {
                    if (timelogs[i][j] > limit) {
                        flag = false;
                        break;
                    }
                }

                day++;
                if (day == 8) day = 1;
            }

            if (flag) answer++;
        }

        return answer;
    }

    private int addTenMinutes(int time) {
        int hour = time / 100;
        int minute = time % 100;

        minute += 10;
        if (minute >= 60) {
            hour++;
            minute -= 60;
        }

        return hour * 100 + minute;
    }
}