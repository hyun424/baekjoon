import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int n = B.length;

        for (int i = 0; i < n; i++) {
            answer += A[i] * B[n - 1 - i];
        }

        return answer;
    }
}