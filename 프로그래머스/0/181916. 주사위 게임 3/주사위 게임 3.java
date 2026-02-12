class Solution {
    public int solution(int a, int b, int c, int d) {
        int[] cnt = new int[7];
        cnt[a]++; cnt[b]++; cnt[c]++; cnt[d]++;

        int p = 0, q = 0, r = 0; // p: 가장 많이 나온 값, q/r: 나머지 값들
        int maxCnt = 0;

        for (int i = 1; i <= 6; i++) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                p = i;
            }
        }

        if (maxCnt == 4) { // 4개 동일
            return 1111 * p;
        }

        if (maxCnt == 3) { // 3개 + 1개
            for (int i = 1; i <= 6; i++) {
                if (cnt[i] == 1) {
                    q = i;
                    break;
                }
            }
            int val = 10 * p + q;
            return val * val;
        }

        if (maxCnt == 2) {
            // 2개 + 2개 인지, 2개 + 1개 + 1개 인지 구분
            int firstPair = 0, secondPair = 0;
            for (int i = 1; i <= 6; i++) {
                if (cnt[i] == 2) {
                    if (firstPair == 0) firstPair = i;
                    else secondPair = i;
                }
            }

            if (secondPair != 0) { // 2개 + 2개
                return (firstPair + secondPair) * Math.abs(firstPair - secondPair);
            } else { // 2개 + 1개 + 1개
                int[] singles = new int[2];
                int idx = 0;
                for (int i = 1; i <= 6; i++) {
                    if (cnt[i] == 1) singles[idx++] = i;
                }
                return singles[0] * singles[1];
            }
        }

        // 모두 다름 (1,1,1,1)
        for (int i = 1; i <= 6; i++) {
            if (cnt[i] == 1) return i; // 최소값부터 찾으면 그게 min
        }

        return 0; // 도달 불가
    }
}
