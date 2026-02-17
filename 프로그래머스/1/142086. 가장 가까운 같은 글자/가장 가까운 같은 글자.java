import java.util.*;

class Solution {
    public int[] solution(String s) {

        int[] answer = new int[s.length()];
        int[] alpha = new int[26];
        Arrays.fill(alpha, -1);

        for (int i = 0; i < s.length(); i++) {

            int idx = s.charAt(i) - 'a';

            if (alpha[idx] == -1) {
                answer[i] = -1;
            } else {
                answer[i] = i - alpha[idx];
            }

            alpha[idx] = i;  // 마지막 위치 갱신
        }

        return answer;
    }
}
