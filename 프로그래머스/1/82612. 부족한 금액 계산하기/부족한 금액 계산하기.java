class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long sum = 1L * price * count * (count + 1) / 2;
        if((long) money >= sum) return 0;
        answer = sum - money;
        return answer;
    }
}

/*
2500 * 2500 = 6,250,000
15,625,000,000
*/