class Solution {
    public int solution(int a, int b) {
        int odd_a = a % 2;
        int odd_b = b % 2;
        if (odd_a == 1 && odd_b == 1){
            return a * a + b * b;
        }else if (odd_a == 0 && odd_b == 0){
            return Math.abs(a - b);
        }else{
            return 2 * ( a + b);
        }
    }
}