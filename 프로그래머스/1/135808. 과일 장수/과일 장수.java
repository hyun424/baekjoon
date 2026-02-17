import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score); // 오름차순

        int answer = 0;
        int n = score.length;

        // 뒤에서부터 m개씩 묶는다
        for (int i = n - m; i >= 0; i -= m) {
            // 이 묶음의 최솟값은 score[i]
            answer += score[i] * m;
        }

        return answer;
    }
}
