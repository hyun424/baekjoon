class Solution {
    public int solution(int a, int b) {
        int mul = 2 * a * b;

        int temp = 1;
        int bb = b;
        while (bb > 0) {
            temp *= 10;
            bb /= 10;
        }

        int concat = a * temp + b;

        return Math.max(concat, mul);
    }
}
