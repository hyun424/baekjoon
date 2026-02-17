class Solution {
    public int solution(int number, int limit, int power) {

        int[] divisor = new int[number + 1];

        for (int i = 1; i <= number; i++) {
            for (int j = i; j <= number; j += i) {
                divisor[j]++;
            }
        }

        int answer = 0;

        for (int i = 1; i <= number; i++) {
            if (divisor[i] > limit) {
                answer += power;
            } else {
                answer += divisor[i];
            }
        }

        return answer;
    }
}
