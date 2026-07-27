import java.util.*;

class Solution {
    private int[] parent;
    private int[] size;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, (a,b) -> (a[2] -b[2]));
        int count = 0;
        parent = new int[n + 1];
        size = new int[n + 1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
            size[i] = 1;
        }
            
        for(int[] cost : costs){
            if(count == n - 1){
                return answer;
            }
            if(union(cost[0], cost[1])){
                answer += cost[2];
                count++;
            }
        }
        return answer;
    }
    
    private int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    private boolean union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        if(size[ra] < size[rb]){
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        size[ra] = size[ra] + size[rb];
        parent[rb] = ra;
        return true;
    }
}