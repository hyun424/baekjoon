import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] indegrees = new int[n + 1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int j = 1; j < x; j++){
                int now = Integer.parseInt(st.nextToken());
                adj.get(prev).add(now);
                indegrees[now]++;
                prev = now;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            if(indegrees[i] == 0){
                q.offer(i);
            }
        }
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int u = q.poll();
            sb.append(u).append("\n");
            flag++;
            for(int i : adj.get(u)){
                indegrees[i]--;
                if(indegrees[i] == 0){
                    q.offer(i);
                }
            }
        }
        if(flag == n){
            System.out.println(sb.toString());
        }
        else{
            System.out.println(0);
        }
        // int n = Integer.parseInt(br.readLine());
    }
}
