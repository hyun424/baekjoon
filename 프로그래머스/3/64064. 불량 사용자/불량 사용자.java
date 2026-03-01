import java.util.*;

class Solution {

    Set<Integer> set = new HashSet<>();
    String[] user_id;
    String[] banned_id;

    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;

        dfs(0, 0);
        return set.size();
    }

    private void dfs(int idx, int mask) {
        // 모든 banned를 처리했다면
        if (idx == banned_id.length) {
            set.add(mask);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {

            // 이미 사용한 user면 skip
            if ((mask & (1 << i)) != 0) continue;

            // 현재 banned 패턴과 매칭되는지 검사
            if (match(user_id[i], banned_id[idx])) {
                dfs(idx + 1, mask | (1 << i));
            }
        }
    }

    private boolean match(String user, String ban) {
        if (user.length() != ban.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) == '*') continue;
            if (user.charAt(i) != ban.charAt(i)) return false;
        }

        return true;
    }
}