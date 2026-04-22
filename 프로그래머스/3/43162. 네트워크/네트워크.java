import java.util.*;

class Solution {
    private static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                q.offer(i);
                answer++;
            }
            while(!q.isEmpty()){
                int cur = q.poll();
                    visited[cur] = true;
                    for(int j = 0; j < n; j++){
                        if(computers[cur][j] == 1 && !visited[j]){
                            q.offer(j);
                        }
                    }
                }
            }
        
        return answer;
    }
}