class Solution {
    public String[] solution(String[] picture, int k) {
        int rows = picture.length;
        int cols = picture[0].length();

        String[] answer = new String[rows * k];
        int idx = 0;

        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();

            // 🔹 가로 k배
            for (int j = 0; j < cols; j++) {
                char ch = picture[i].charAt(j);
                for (int t = 0; t < k; t++) {
                    sb.append(ch);
                }
            }

            String expandedRow = sb.toString();

            // 🔹 세로 k배
            for (int t = 0; t < k; t++) {
                answer[idx++] = expandedRow;
            }
        }

        return answer;
    }
}
