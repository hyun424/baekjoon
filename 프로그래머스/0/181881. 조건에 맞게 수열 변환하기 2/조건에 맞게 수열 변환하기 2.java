import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        int x = 0;
        int[] cur = arr;

        while (true) {
            int[] next = new int[cur.length];

            for (int i = 0; i < cur.length; i++) {
                int v = cur[i];
                if (v >= 50 && v % 2 == 0) {
                    next[i] = v / 2;
                } else if (v < 50 && v % 2 == 1) {
                    next[i] = v * 2 + 1;
                } else {
                    next[i] = v;
                }
            }

            if (Arrays.equals(cur, next)) {
                return x;
            }

            cur = next;
            x++;
        }
    }
}
