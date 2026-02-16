class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int i = 0; 
        while (i < section.length){
            int start = section[i];
            int coverEnd = start + m - 1;
            
            answer++;
            
            while (i < section.length && section[i] <= coverEnd){
                i++;
            }
        }
        return answer;
    }
}