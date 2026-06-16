import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.offer((long) s);
        }

        int answer = 0;

        while (pq.size() >= 2 && pq.peek() < K) {
            long first = pq.poll();
            long second = pq.poll();

            pq.offer(first + second * 2);
            answer++;
        }

        if (pq.peek() >= K) {
            return answer;
        }

        return -1;
    }
}