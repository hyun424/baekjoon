import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>(); // 인덱스 스택

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peekLast()]) {
                int prev = stack.pollLast();
                answer[prev] = i - prev;
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int prev = stack.pollLast();
            answer[prev] = n - 1 - prev;
        }

        return answer;
    }
}
