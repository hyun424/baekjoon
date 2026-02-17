import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap

        for (int i = 0; i < score.length; i++) {
            pq.offer(score[i]);

            if (pq.size() > k) pq.poll();   // 상위 k만 유지

            answer[i] = pq.peek();          // 현재 명예의 전당 최하위 점수
        }

        return answer;
    }
}
