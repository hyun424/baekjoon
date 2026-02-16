import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {

        Map<String, Integer> rank = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }

        for (String name : callings) {

            int curIdx = rank.get(name);
            int frontIdx = curIdx - 1;

            String frontPlayer = players[frontIdx];

            // swap
            players[frontIdx] = name;
            players[curIdx] = frontPlayer;

            // update map
            rank.put(name, frontIdx);
            rank.put(frontPlayer, curIdx);
        }

        return players;
    }
}
