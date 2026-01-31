class Solution {
    public int solution(int a, int b, int c) {
        int sum1 = a + b + c;
        int sum2 = a*a + b*b + c*c;
        int sum3 = a*a*a + b*b*b + c*c*c;

        if (a == b && b == c) {
            return sum1 * sum2 * sum3;
        } else if (a == b || b == c || a == c) {
            return sum1 * sum2;
        } else {
            return sum1;
        }
    }
}
