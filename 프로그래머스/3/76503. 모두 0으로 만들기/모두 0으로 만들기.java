import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int n = a.length;

        long[] weight = new long[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
            sum += weight[i];
        }

        if (sum != 0) return -1;
        if (n == 1) return 0;

        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];
        boolean[] removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        long answer = 0;

        while (!queue.isEmpty()) {
            int leaf = queue.poll();

            if (removed[leaf]) continue;
            removed[leaf] = true;

            for (int next : graph[leaf]) {
                if (removed[next]) continue;

                answer += Math.abs(weight[leaf]);
                weight[next] += weight[leaf];
                weight[leaf] = 0;

                degree[next]--;
                if (degree[next] == 1) {
                    queue.offer(next);
                }
                break;
            }
        }

        return answer;
    }
}