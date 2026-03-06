class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        int n = arr.length;
        double sum = 0;
        for (int i = 0; i < n; i++){
            sum += arr[i];
        }
        
        return sum / n;
    }
}