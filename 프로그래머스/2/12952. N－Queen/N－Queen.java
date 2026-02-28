class Solution {
    int count = 0;
    boolean[] col;
    boolean[] diag1;
    boolean[] diag2;

    public int solution(int n) {
        col = new boolean[n];
        diag1 = new boolean[2*n];
        diag2 = new boolean[2*n];
        
        dfs(0, n);
        return count;
    }

    void dfs(int row, int n) {
        if (row == n) {
            count++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if (col[c] || diag1[row - c + n] || diag2[row + c])
                continue;

            col[c] = true;
            diag1[row - c + n] = true;
            diag2[row + c] = true;

            dfs(row + 1, n);

            col[c] = false;
            diag1[row - c + n] = false;
            diag2[row + c] = false;
        }
    }
}