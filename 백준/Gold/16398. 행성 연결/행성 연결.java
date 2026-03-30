import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        int[][] graph = new int[n * (n - 1) / 2][3];
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;

        }
        int idx = 0;
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                int cost = Integer.parseInt(st.nextToken());
                if (i < j){
                    graph[idx][0] = i;
                    graph[idx][1] = j;
                    graph[idx][2] = cost;
                    idx++;
                }
            }
        }
        Arrays.sort(graph, (a,b) -> Integer.compare(a[2],b[2]));
        long answer = 0;
        int count = 0;
        for(int i = 0; i < idx; i++){
            if(union(graph[i][0], graph[i][1])){
                answer += graph[i][2];
                count++;
                if(count == n - 1) break;
            }
        }
        System.out.println(answer);
        // int n = Integer.parseInt(br.readLine());
    }
}
