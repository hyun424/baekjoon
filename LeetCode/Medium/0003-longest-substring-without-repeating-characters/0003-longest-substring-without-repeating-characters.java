class Solution {
    public int lengthOfLongestSubstring(String s) {
        StringBuilder window = new StringBuilder();
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);

            // current와 같은 글자가 없어질 때까지 앞에서 제거
            while (window.indexOf(String.valueOf(current)) != -1) {
                window.deleteCharAt(0);
            }

            // 중복이 사라졌으므로 현재 글자 추가
            window.append(current);

            maxLength = Math.max(maxLength, window.length());
        }

        return maxLength;
    }
}