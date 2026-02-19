import java.util.*;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {

        List<Integer> answer = new ArrayList<>();

        int n = progresses.length;

  
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            int remain = 100 - progresses[i];
            days[i] = (remain + speeds[i] - 1) / speeds[i]; // 올림
        }

        int prev = days[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (days[i] <= prev) {
                count++;
            } else {
                answer.add(count);
                count = 1;
                prev = days[i];
            }
        }

        answer.add(count);

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
