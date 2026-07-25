class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int start = 0;
        int end = 0;
        int sum = 0;
        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;
        while(true){
            if (sum >= k){
                if (sum == k){
                    int currentLength = end - start;
                    
                    if(currentLength < minLength){
                        minLength = currentLength;
                        answer[0] = start;
                        answer[1] = end - 1;
                    }
                }
                sum -= sequence[start];
                start++;
            }else{
                if(end == sequence.length){
                    break;
                }
                sum += sequence[end];
                end++;
            }
        }
        return answer;
    }
}