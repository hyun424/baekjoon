class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = {"aya", "ye", "woo", "ma"};

        for (String s : babbling) {
            int idx = 0;
            String prev = "";
            boolean ok = true;

            while (idx < s.length()) {
                boolean matched = false;

                for (String word : words) {
                    if (!word.equals(prev) && s.startsWith(word, idx)) {
                        idx += word.length();
                        prev = word;
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    ok = false;
                    break;
                }
            }

            if (ok) answer++;
        }

        return answer;
    }
}