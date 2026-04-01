import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int find(int x, int[] parent){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x], parent);
    }
    private static boolean union(int a, int b, int[] parent){
        int ua  = find(a, parent);
        int ub = find(b, parent);
        if (ua == ub) return false;
        parent[ua] = ub;
        return true;
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] max_graph = new int[m + 1][3];
        int[][] min_graph = new int[m + 1][3];

        for (int i = 0; i < m + 1; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = 1 - Integer.parseInt(st.nextToken());
            max_graph[i][0] = a;
            max_graph[i][1] = b;
            max_graph[i][2] = c;
            min_graph[i][0] = a;
            min_graph[i][1] = b;
            min_graph[i][2] = c;
        }
        int[] max_parent = new int[n + 1];
        int[] min_parent = new int[n + 1];
        for (int i = 0; i < n + 1; i ++){
            max_parent[i] = i;
            min_parent[i] = i;
        }
        Arrays.sort(max_graph,(a,b) -> Integer.compare(-a[2], -b[2]));
        Arrays.sort(min_graph,(a,b) -> Integer.compare(a[2], b[2]));
        long answer1 = 0;
        int count1 = 0;
        for(int i = 0; i < m + 1; i++) {
            if (union(max_graph[i][0], max_graph[i][1], max_parent)) {
                answer1 += max_graph[i][2];
                count1++;
                if (count1 == n) break;
            }
        }
        long answer2 = 0;
        int count2 = 0;
        for(int i = 0; i < m + 1; i++) {
            if (union(min_graph[i][0], min_graph[i][1], min_parent)) {
                answer2 += min_graph[i][2];
                count2++;
                if (count2 == n) break;
            }
        }
        System.out.println(answer1 *  answer1 - answer2 * answer2);
        // int n = Integer.parseInt(br.readLine());
    }
}
