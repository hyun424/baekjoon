class Solution {
    public String solution(String s, String skip, int index) {
        boolean[] blocked = new boolean[26];
        for (int i = 0; i < skip.length(); i++) {
            blocked[skip.charAt(i) - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            int cnt = 0;

            while (cnt < index) {
                cur = (cur + 1) % 26;
                if (!blocked[cur]) cnt++;
            }

            sb.append((char) ('a' + cur));
        }

        return sb.toString();
    }
}
