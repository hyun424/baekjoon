class Solution {
    public String solution(String number, int k) {
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);

            while (
                stack.length() > 0 &&
                k > 0 &&
                stack.charAt(stack.length() - 1) < current
            ) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(current);
        }

        // 숫자가 내림차순이면 앞 과정에서 삭제되지 않으므로 뒤에서 제거
        if (k > 0) {
            stack.setLength(stack.length() - k);
        }

        return stack.toString();
    }
}