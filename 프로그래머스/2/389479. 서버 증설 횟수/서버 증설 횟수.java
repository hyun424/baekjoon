class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int n = 0;
        int index = 0;
        int[] destroy = new int[k];
        
        for(int player : players){
            
            n = n - destroy[index % k];
            destroy[index % k] = 0;
            
            int required = player / m;
            
            if(required > n){
                int add = required - n;
                
                n+= add;
                destroy[index % k ]  = add;
                answer += add;
                
            }
            index++;
        }
        return answer;
    }
}