class Solution {
    public int solution(String s) {
        int answer = 0;
        char x = 0;
        int same = 0, diff = 0;

        for (int i = 0; i < s.length(); i++) {
            if (same == 0) { // 새 조각 시작
                x = s.charAt(i);
                answer++;
            }
            if (s.charAt(i) == x) same++;
            else diff++;

            if (same == diff) { // 조각 끝
                same = 0;
                diff = 0;
            }
        }
        return answer;
    }
}
