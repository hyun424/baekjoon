class Solution {
    public int[] solution(int brown, int yellow) {

        int nPlusM = (brown + 4) / 2; // n + m

        for (int m = 1; m <= nPlusM / 2; m++) {

            int n = nPlusM - m;

            if (n * m == brown + yellow) {
                return new int[]{n, m};
            }
        }

        return new int[]{};
    }
}
