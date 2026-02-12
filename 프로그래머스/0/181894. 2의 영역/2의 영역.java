import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {

        int start = -1;
        int end = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                if (start == -1) {
                    start = i;   // 첫 2
                }
                end = i;         // 마지막 2 계속 갱신
            }
        }

        if (start == -1) {
            return new int[]{-1};
        }

        return Arrays.copyOfRange(arr, start, end + 1);
    }
}
