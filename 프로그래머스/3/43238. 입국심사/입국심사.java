import java.util.*;

class Solution {
    public long solution(int n, int[] times) {

        long left = 1;

        int min = Integer.MAX_VALUE;
        for (int t : times) {
            min = Math.min(min, t);
        }

        long right = (long) min * n;

        long answer = right;

        while (left <= right) {

            long mid = (left + right) / 2;

            long processed = 0;

            for (int t : times) {
                processed += mid / t;
                if (processed >= n) break;
            }

            if (processed >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}