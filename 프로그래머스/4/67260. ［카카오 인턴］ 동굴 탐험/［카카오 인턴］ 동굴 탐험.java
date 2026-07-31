import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : path){
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int[] before = new int[n];
        Arrays.fill(before, -1);
        for(int[] condition : order){
            int first = condition[0];
            int later = condition[1];
            
            before[later] = first;
        }
        
        if(before[0] != -1){
            return false;
        }
        
        int[] waiting = new int[n];
        Arrays.fill(waiting, -1);
        
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[0] = true;
        queue.offer(0);
        
        int visitedCount = 1;
        
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            
            for(int next : graph[current]){
                if(visited[next]){
                    continue;
                }
                int requiredRoom = before[next];
                
                if(requiredRoom != -1 && !visited[requiredRoom]){
                    waiting[requiredRoom] = next;
                    continue;
                }
                
                visited[next] = true;
                visitedCount++;
                queue.offer(next);
                
                if(waiting[next] != -1){
                    int unlockedRoom = waiting[next];
                    
                    if(!visited[unlockedRoom]){
                        visited[unlockedRoom] = true;
                        visitedCount++;
                        queue.offer(unlockedRoom);
                    }
                    
                    waiting[next ] = -1;
                }
            }
        }
        
        return visitedCount == n;
    }
}