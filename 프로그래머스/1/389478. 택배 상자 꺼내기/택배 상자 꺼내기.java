class Solution {
    public int solution(int n, int w, int num) {

        int row = (num - 1) / w;
        int pos = (num - 1) % w;

        int col;
        if (row % 2 == 0) {
            col = pos;
        } else {
            col = w - 1 - pos;
        }

        int totalRows = (n - 1) / w;
        int answer = 1;

        for (int r = row + 1; r <= totalRows; r++) {

            int start = r * w + 1;
            int end = Math.min(n, (r + 1) * w);

            int count = end - start + 1;

            if (count <= 0) continue;

            int checkCol;
            if (r % 2 == 0) {
                checkCol = col;
            } else {
                checkCol = w - 1 - col;
            }

            if (checkCol < count) {
                answer++;
            }
        }

        return answer;
    }
}
