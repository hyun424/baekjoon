class Solution {
    public int[] solution(int[] arr, int n) {
        int[] answer = {};
        int size = arr.length;
        if (size % 2 == 1){
            for (int i = 0; i < size; i+=2){
                arr[i] += n;
            }
        }else{
            for (int i = 1; i < size; i+=2){
                arr[i] += n;
            }
        }
        return arr;
    }
}