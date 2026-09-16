import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int lastEnd = -1;

        for (int[] target : targets) {
            if (target[0] >= lastEnd) {
                answer++;
                lastEnd = target[1];
            }
        }

        return answer;
    }
}