import java.util.*;

class Solution {
    public int solution(int dist_limit, int split_limit) {
        long D = dist_limit;
        long S = split_limit;

        // 완전하게 채운 상태에서 가능한 리프 수들: 2^a * 3^b <= split_limit
        ArrayList<Long> vals = new ArrayList<>();
        for (long a = 1; a <= S; a *= 2) {
            for (long v = a; v <= S; v *= 3) {
                vals.add(v);
                if (v > S / 3) break;
            }
            if (a > S / 2) break;
        }

        Collections.sort(vals);

        ArrayList<Long> uniq = new ArrayList<>();
        long prev = -1;
        for (long v : vals) {
            if (v != prev) {
                uniq.add(v);
                prev = v;
            }
        }

        long INF = (long) 4e18;
        HashMap<Long, Long> cost = new HashMap<>();

        for (long v : uniq) cost.put(v, INF);
        cost.put(1L, 0L);

        // cost[p] = 리프가 정확히 p개인 "완전 상태"를 만드는 최소 분배 노드 수
        for (long p : uniq) {
            if (p == 1) continue;

            long best = INF;

            if (p % 2 == 0) {
                Long prevCost = cost.get(p / 2);
                if (prevCost != null) {
                    best = Math.min(best, prevCost + p / 2);
                }
            }

            if (p % 3 == 0) {
                Long prevCost = cost.get(p / 3);
                if (prevCost != null) {
                    best = Math.min(best, prevCost + p / 3);
                }
            }

            cost.put(p, best);
        }

        long answer = 1;

        // 완전 상태 p를 만든 뒤,
        // 마지막 한 층만 부분 확장하면 된다.
        for (long p : uniq) {
            long used = cost.get(p);
            if (used > D) continue;

            long remain = D - used;

            // 더 안 확장하는 경우
            answer = Math.max(answer, p);

            // 마지막 층에서 2분배 부분 확장
            if (p <= S / 2) {
                long x = Math.min(remain, p);   // p개의 리프 중 x개만 확장 가능
                answer = Math.max(answer, p + x);
            }

            // 마지막 층에서 3분배 부분 확장
            if (p <= S / 3) {
                long x = Math.min(remain, p);
                answer = Math.max(answer, p + 2L * x);
            }
        }

        return (int) answer;
    }
}