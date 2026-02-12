class Solution {
    public int solution(String[] strArr) {
        int[] cnt = new int[31]; // 길이 1~30
        int max = 0;

        for (String s : strArr) {
            int len = s.length();
            int v = ++cnt[len];
            if (v > max) max = v;
        }

        return max;
    }
}
