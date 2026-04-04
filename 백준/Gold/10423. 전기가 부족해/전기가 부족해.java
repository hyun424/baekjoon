import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[][] graph;
    private static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
    private static boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV) return false;
        if (rootU == 0) {
            parent[rootV] = 0;
        } else if (rootV == 0) {
            parent[rootU] = 0;
        } else {
            parent[rootU] = rootV;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++){
            parent[Integer.parseInt(st.nextToken())] = 0;
        }
        graph = new int[m][3];
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[i][0] = u;
            graph[i][1] = v;
            graph[i][2] = w;
        }
        Arrays.sort(graph,(a,b) -> Integer.compare(a[2], b[2]));
        long answer = 0;
        int count = 0;
        for(int i = 0; i < m; i++){
            if(union(graph[i][0], graph[i][1])){
                answer += graph[i][2];
                count++;
                if(count == n - k) break;
            }
        }
        System.out.println(answer);
        // int n = Integer.parseInt(br.readLine());
    }
}
