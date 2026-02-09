class Solution {
    public int[] solution(int n, int k) {
        int size = n / k;
        int[] answer = new int[size];

        int idx = 0;
        for (int i = k; i <= n; i += k) {
            answer[idx++] = i;
        }
        return answer;
    }
}
