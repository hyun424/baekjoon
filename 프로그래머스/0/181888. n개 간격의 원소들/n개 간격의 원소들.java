class Solution {
    public int[] solution(int[] num_list, int n) {

        int len = num_list.length;
        int size = (len - 1) / n + 1;
        
        int[] answer = new int[size];
        int idx = 0;
        
        for ( int i = 0; i <len; i+=n){
            answer[idx++] = num_list[i];
        }
        return answer;
    }
}