class Solution {
    public String solution(String my_string, int[][] queries) {

        for (int[] q : queries) {
            int s = q[0];
            int e = q[1];

            // 1. 앞부분
            String start = my_string.substring(0, s);

            // 2. 뒤집을 부분
            String mid = new StringBuilder(
                    my_string.substring(s, e + 1)
            ).reverse().toString();

            // 3. 뒷부분
            String end = my_string.substring(e + 1);

            // 다시 합치기
            my_string = start + mid + end;
        }

        return my_string;
    }
}
