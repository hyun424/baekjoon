import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    private static boolean union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        parent[rb] = ra;
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[m][3];

        parent = new int[n + 1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int idx = 0;
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[idx][0] = a;
            graph[idx][1] = b;
            graph[idx][2] = cost;
            idx++;
        }
        Arrays.sort(graph, (a, b) -> Integer.compare(a[2],b[2]));
        long answer = 0;
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (count == n - 2) break;
            if ((union(graph[i][0], graph[i][1]))) {
                answer += graph[i][2];
                count++;
            }
        }

        System.out.println(answer);
        // int n = Integer.parseInt(br.readLine());
    }
}
