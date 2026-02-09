class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        if ("<".equals(ineq)) {
            if ("=".equals(eq)) return n <= m ? 1 : 0;
            else return n < m ? 1 : 0;
        } else { // ">"
            if ("=".equals(eq)) return n >= m ? 1 : 0;
            else return n > m ? 1 : 0;
        }
    }
}
