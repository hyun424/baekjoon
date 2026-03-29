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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] edges = new int[e][3];
        parent = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = c;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        long answer = 0;
        int count = 0;
        for (int i = 0; i < e; i++) {
            if (union(edges[i][0], edges[i][1])) {
                answer += edges[i][2];
                count++;
                if (count == v - 1) break;
            }
        }

        System.out.println(answer);
    }
}
