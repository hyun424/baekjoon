import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        HashMap<String, Integer> rank = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }
        for (String call : callings) {
            int curIdx = rank.get(call);
            int frontIdx = curIdx - 1;

            String frontPlayer = players[frontIdx];

            // swap
            players[frontIdx] = call;
            players[curIdx] = frontPlayer;

            // map 갱신
            rank.put(call, frontIdx);
            rank.put(frontPlayer, curIdx);
        }
        return players;
    }
}