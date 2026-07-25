import java.util.*;

class Solution {
    private int[] parent;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(edge -> edge[2]));
        
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        int answer = 0;
        int connected = 0;
        
        for (int[] edge : costs){
            int islandA = edge[0];
            int islandB  = edge[1];
            int cost = edge[2];
            
            if(find(islandA) == find(islandB)){
                continue;
            }
            union(islandA, islandB);
            answer += cost;
            connected++;
            
            if(connected == n - 1){
                break;
            }
        }
        return answer;
    }
    
    private int find(int x){
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        parent[rootB] = rootA;
    }
}