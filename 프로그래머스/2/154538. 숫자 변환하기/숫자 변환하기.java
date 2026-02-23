import java.util.*;

class Solution {

    public int solution(int x, int y, int n) {

        boolean[] visited = new boolean[y + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(x);
        visited[x] = true;

        int depth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int cur = queue.poll();

                if (cur == y) return depth;

                int[] next = {cur + n, cur * 2, cur * 3};

                for (int num : next) {
                    if (num <= y && !visited[num]) {
                        visited[num] = true;
                        queue.offer(num);
                    }
                }
            }

            depth++;
        }

        return -1;
    }
}