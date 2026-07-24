import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes,(a, b) -> Integer.compare(a[1], b[1]));
        int start = routes[0][0];
        int end = routes[0][1];
        
        for (int[] route : routes){
            if (end < route[0]){
                start = route[0];
                end = route[1];
                answer++;
            }else{
                if(end == route[0]){
                    start = route[0];
                    end = Math.min(end, route[1]);
                }else{
                    start = route[0];
                    if (end > route[1]){
                        end = route[1];
                    }
                }
            }
            
        }
        return answer;
    }
}