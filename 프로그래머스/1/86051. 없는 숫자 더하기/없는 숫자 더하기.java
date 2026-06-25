class Solution {
    public int solution(int[] numbers){
        boolean[] exist = new boolean[10];
        
        for(int n : numbers){
            exist[n] = true;
        }
        int answer = 0;
        for(int i = 0; i <= 9; i++){
            if(!exist[i]) answer += i; 
        } 
        return answer;
     }
}