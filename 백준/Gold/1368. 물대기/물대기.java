import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int edgeCount = n + (n * (n - 1)) / 2;
        int[][] edges = new int[edgeCount][3];
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            int cost = Integer.parseInt(br.readLine());
            edges[idx][0] = 0;
            edges[idx][1] = i;
            edges[idx][2] = cost;
            idx++;
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i < j) {
                    edges[idx][0] = i;
                    edges[idx][1] = j;
                    edges[idx][2] = cost;
                    idx++;
                }
            }
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        long answer = 0;
        int count = 0;
        for (int i = 0; i < edgeCount; i++) {
            if (union(edges[i][0], edges[i][1])) {
                answer += edges[i][2];
                count++;
                if (count == n) break;
            }
        }

        System.out.println(answer);
    }
}
