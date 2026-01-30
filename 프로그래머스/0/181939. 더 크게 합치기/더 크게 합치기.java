class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int concatAB = Integer.parseInt("" + a + b);
        int concatBA = Integer.parseInt("" + b + a);
        if (concatAB >= concatBA){
            answer = concatAB;
        }
        else{
            answer = concatBA;
        }
        return answer;
    }
}