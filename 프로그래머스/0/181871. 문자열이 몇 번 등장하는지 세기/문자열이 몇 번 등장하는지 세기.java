class Solution {
    public int solution(String myString, String pat) {
        int n = myString.length();
        int m = pat.length();
        int cnt = 0;

        for (int i = 0; i <= n - m; i++) {
            boolean ok = true;
            for (int j = 0; j < m; j++) {
                if (myString.charAt(i + j) != pat.charAt(j)) {
                    ok = false;
                    break;
                }
            }
            if (ok) cnt++;
        }
        return cnt;
    }
}
