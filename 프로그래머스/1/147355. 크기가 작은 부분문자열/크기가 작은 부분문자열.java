class Solution {
    public int solution(String t, String p) {
        int k = p.length();
        long pVal = Long.parseLong(p);
        int cnt = 0;

        for (int i = 0; i <= t.length() - k; i++) {
            long val = Long.parseLong(t.substring(i, i + k));
            if (val <= pVal) cnt++;
        }
        return cnt;
    }
}
