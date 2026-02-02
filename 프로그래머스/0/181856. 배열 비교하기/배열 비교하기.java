class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int size1 = arr1.length;
        int size2 = arr2.length;

        if (size1 != size2) {
            return size1 > size2 ? 1 : -1;
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int x : arr1) sum1 += x;
        for (int y : arr2) sum2 += y;

        if (sum1 == sum2) return 0;
        return sum1 > sum2 ? 1 : -1;
    }
}
