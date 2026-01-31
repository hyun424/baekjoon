class Solution {
    public int[] solution(int[] arr, int k) {
        int size = arr.length;
        int[] answer = new int[size];

        if (k % 2 == 1) { // 홀수
            for (int i = 0; i < size; i++) {
                answer[i] = arr[i] * k;
            }
        } else { // 짝수
            for (int i = 0; i < size; i++) {
                answer[i] = arr[i] + k;
            }
        }

        return answer;
    }
}
