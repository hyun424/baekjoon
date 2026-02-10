import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        int n = my_string.length();
        String[] answer = new String[n];

        // 1. 모든 접미사 생성
        for (int i = 0; i < n; i++) {
            answer[i] = my_string.substring(i);
        }

        // 2. 사전순 정렬
        Arrays.sort(answer);

        return answer;
    }
}
