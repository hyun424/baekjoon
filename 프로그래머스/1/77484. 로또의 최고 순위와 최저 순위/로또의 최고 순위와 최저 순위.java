class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int temp0 = 0;
        int temp1 = 0;
        for(int i = 0; i < 6; i++){
            if(lottos[i] == 0) temp0++;
            
            for(int j = 0; j < 6; j++){
                if(lottos[i] == win_nums[j]){
                    temp1++;
                }
            }
        }
        if(temp1 < 2) {
            answer[1] = 6;
        }else{
            answer[1] = 7 - temp1;
        }
        
        if((temp0 + temp1) < 2){
            answer[0] = 6;
        }else{
            answer[0] = 7 - (temp0 + temp1);
        }
        
        
        return answer; 
    }
}