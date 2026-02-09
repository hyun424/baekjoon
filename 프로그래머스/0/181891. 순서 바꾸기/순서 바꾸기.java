class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] answer = new int[num_list.length];
        int idx = 0;

        // n 이후
        for (int i = n; i < num_list.length; i++) {
            answer[idx++] = num_list[i];
        }

        // 0 ~ n-1
        for (int i = 0; i < n; i++) {
            answer[idx++] = num_list[i];
        }
    return answer;
    }
}