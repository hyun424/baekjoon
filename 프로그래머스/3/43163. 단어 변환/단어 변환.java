import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // target이 words에 없으면 불가능
        boolean exists = false;
        for (String w : words) {
            if (w.equals(target)) { exists = true; break; }
        }
        if (!exists) return 0;

        Queue<String> q = new ArrayDeque<>();
        Queue<Integer> depth = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        q.add(begin);
        depth.add(0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int d = depth.poll();

            if (cur.equals(target)) return d;

            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                if (diffOne(cur, words[i])) {
                    visited[i] = true;
                    q.add(words[i]);
                    depth.add(d + 1);
                }
            }
        }

        return 0;
    }

    private boolean diffOne(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}
