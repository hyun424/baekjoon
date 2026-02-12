import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {

        int size = arr.length;
        int n = 1;
        while (size > n) n *= 2;

        return Arrays.copyOf(arr, n);
    }
}
