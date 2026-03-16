import java.util.*;

class Solution {
    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        int count = 0;

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];

            if (find(a) != find(b)) {
                union(a, b);
                answer += c;
                count++;

                if (count == n - 1) {
                    break;
                }
            }
        }

        return answer;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}