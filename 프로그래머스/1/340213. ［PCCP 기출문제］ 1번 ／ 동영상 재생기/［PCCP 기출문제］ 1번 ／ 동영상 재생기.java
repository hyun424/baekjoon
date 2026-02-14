class Solution {

    public String solution(String video_len, String pos,
                           String op_start, String op_end,
                           String[] commands) {

        int videoLen = toSec(video_len);
        int cur = toSec(pos);
        int opStart = toSec(op_start);
        int opEnd = toSec(op_end);

        // 시작 위치가 오프닝이면 먼저 이동
        if (cur >= opStart && cur <= opEnd) {
            cur = opEnd;
        }

        for (String command : commands) {

            if (command.equals("prev")) {
                cur = Math.max(0, cur - 10);
            } else {
                cur = Math.min(videoLen, cur + 10);
            }

            // 이동 후 오프닝 체크
            if (cur >= opStart && cur <= opEnd) {
                cur = opEnd;
            }
        }

        return String.format("%02d:%02d", cur / 60, cur % 60);
    }

    private int toSec(String t) {
        return Integer.parseInt(t.substring(0,2)) * 60
             + Integer.parseInt(t.substring(3,5));
    }
}
