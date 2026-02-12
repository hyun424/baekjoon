class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int s = queries[qi][0];
            int e = queries[qi][1];
            int k = queries[qi][2];

            int min = Integer.MAX_VALUE;

            for (int i = s; i <= e; i++) {
                if (arr[i] > k) {
                    min = Math.min(min, arr[i]);
                }
            }

            answer[qi] = (min == Integer.MAX_VALUE) ? -1 : min;
        }

        return answer;
    }
}
