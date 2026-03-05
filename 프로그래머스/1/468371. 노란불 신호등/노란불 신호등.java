import java.util.*;

class Solution {

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public int solution(int[][] signals) {
        int n = signals.length;

        // 1) 전체 LCM 계산
        int M = 1;
        int[] cycles = new int[n];
        for (int i = 0; i < n; i++) {
            int G = signals[i][0], Y = signals[i][1], R = signals[i][2];
            cycles[i] = G + Y + R;
            M = lcm(M, cycles[i]);
        }

        // 2) 가능한 시간(1..M) 중 "모든 신호등이 노란불"인 최소 t를 찾는다.
        //    최적화를 위해, step-by-step로 가능한 나머지(1..mod)만 유지한다.

        // mod=cycles[0] 기준으로 가능한 residue(1..mod) 만들기
        int mod = cycles[0];
        boolean[] possible = new boolean[mod + 1]; // index = t (1..mod)
        {
            int G = signals[0][0], Y = signals[0][1];
            for (int t = 1; t <= mod; t++) {
                int x = (t - 1) % mod; // == (t-1) mod cycle
                if (G <= x && x < G + Y) possible[t] = true;
            }
        }

        // 3) 나머지 신호등을 하나씩 합치면서 mod를 lcm으로 확장
        for (int i = 1; i < n; i++) {
            int c = cycles[i];
            int newMod = lcm(mod, c);

            boolean[] next = new boolean[newMod + 1];

            int G = signals[i][0], Y = signals[i][1];

            // t = 1..newMod 에 대해
            // - 이전 조건: possible[(t-1)%mod + 1] == true
            // - 현재 신호등 노란불: G <= (t-1)%c < G+Y
            for (int t = 1; t <= newMod; t++) {
                int prevT = ((t - 1) % mod) + 1;
                if (!possible[prevT]) continue;

                int x = (t - 1) % c;
                if (G <= x && x < G + Y) next[t] = true;
            }

            possible = next;
            mod = newMod;
        }

        // 4) 최종 mod는 전체 LCM(M)과 같아짐(동일하지 않아도 mod 주기로 반복이 보장됨)
        // 가능한 t 중 최소를 반환
        for (int t = 1; t <= mod; t++) {
            if (possible[t]) return t;
        }
        return -1;
    }
}