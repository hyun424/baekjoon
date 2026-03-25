import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int N;
    static boolean[] visited;
    static int res = 0;

    private static int findDepth() {
        int cur = 1;
        int depth = 0;

        while (graph[cur][1] != -1) {
            cur = graph[cur][1];
            depth++;
        }
        return depth;
    }

    private static void tree(int parent){
        int left = graph[parent][0];
        int right = graph[parent][1];
        visited[parent] = true;

        if (left != -1 && !visited[left]) {
            res += 2;
            tree(left);
        }
        if (right != -1 && !visited[right]) {
            res += 2;
            tree(right);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            graph[i][0] = -1;
            graph[i][1] = -1;
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][0] = b;
            graph[a][1] = c;
        }
        visited = new boolean[N + 1];
        tree(1);
        System.out.println(res - findDepth());
    }
}
