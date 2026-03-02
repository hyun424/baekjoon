import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;

     
        boolean[] visited = new boolean[n];

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;    

            answer++;                    
            visited[i] = true;
            q.offer(new int[] { i });     

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int node = cur[0];

                for (int next = 0; next < n; next++) {
                    if (computers[node][next] == 1 && !visited[next]) {
                        visited[next] = true;
                        q.offer(new int[] { next });
                    }
                }
            }
        }

        return answer;
    }
}