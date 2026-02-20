import java.util.*;

class Solution {
    boolean[] used;
    List<String> path = new ArrayList<>();
    String[][] tickets;
    int n;
    boolean found = false;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        n = tickets.length;
        used = new boolean[n];

        // 도착지 기준 사전순 정렬
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        path.add("ICN");
        dfs("ICN", 0);

        return path.toArray(new String[0]);
    }

    private void dfs(String cur, int depth) {
        if (depth == n) {
            found = true;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i] && tickets[i][0].equals(cur)) {
                used[i] = true;
                path.add(tickets[i][1]);

                dfs(tickets[i][1], depth + 1);

                if (found) return; // 정답 찾으면 종료

                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
