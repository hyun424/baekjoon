import java.util.*;

class Solution {

    static class State {
        int idx;
        int sum;

        State(int idx, int sum) {
            this.idx = idx;
            this.sum = sum;
        }
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(0, 0));

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.idx == numbers.length) {
                if (cur.sum == target) {
                    answer++;
                }
                continue;
            }

            int num = numbers[cur.idx];

            q.offer(new State(cur.idx + 1, cur.sum + num));
            q.offer(new State(cur.idx + 1, cur.sum - num));
        }

        return answer;
    }
}