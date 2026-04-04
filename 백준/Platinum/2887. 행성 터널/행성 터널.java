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

        int[][] planet = new int[n][4];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planet[i][0] = i;
            planet[i][1] = Integer.parseInt(st.nextToken());
            planet[i][2] = Integer.parseInt(st.nextToken());
            planet[i][3] = Integer.parseInt(st.nextToken());
        }

        int[][] edges = new int[3 * (n - 1)][3];
        int idx = 0;

        Arrays.sort(planet, (a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 0; i < n - 1; i++) {
            edges[idx][0] = planet[i][0];
            edges[idx][1] = planet[i + 1][0];
            edges[idx][2] = Math.abs(planet[i][1] - planet[i + 1][1]);
            idx++;
        }

        Arrays.sort(planet, (a, b) -> Integer.compare(a[2], b[2]));
        for (int i = 0; i < n - 1; i++) {
            edges[idx][0] = planet[i][0];
            edges[idx][1] = planet[i + 1][0];
            edges[idx][2] = Math.abs(planet[i][2] - planet[i + 1][2]);
            idx++;
        }

        Arrays.sort(planet, (a, b) -> Integer.compare(a[3], b[3]));
        for (int i = 0; i < n - 1; i++) {
            edges[idx][0] = planet[i][0];
            edges[idx][1] = planet[i + 1][0];
            edges[idx][2] = Math.abs(planet[i][3] - planet[i + 1][3]);
            idx++;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        long answer = 0;
        int count = 0;

        for (int i = 0; i < edges.length; i++) {
            if (union(edges[i][0], edges[i][1])) {
                answer += edges[i][2];
                count++;
                if (count == n - 1) break;
            }
        }

        System.out.println(answer);
    }
}
