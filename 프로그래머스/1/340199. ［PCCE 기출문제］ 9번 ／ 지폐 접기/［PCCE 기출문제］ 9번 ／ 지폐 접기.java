class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        int wMin = Math.min(wallet[0], wallet[1]);
        int wMax = Math.max(wallet[0], wallet[1]);

        int b0 = bill[0], b1 = bill[1];

        while (true) {
            int bMin = Math.min(b0, b1);
            int bMax = Math.max(b0, b1);

            // 90도 회전 가능하므로 정렬 비교로 충분
            if (bMin <= wMin && bMax <= wMax) break;

            // 긴 변을 접는다
            if (b0 >= b1) b0 /= 2;
            else b1 /= 2;

            answer++;
        }

        return answer;
    }
}
