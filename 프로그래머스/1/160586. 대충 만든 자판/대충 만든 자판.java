import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {

        int[] answer = new int[targets.length];

        // 문자별 최소 누름 횟수
        Map<Character, Integer> minPress = new HashMap<>();

        // 1) keymap 전처리: 각 문자에 대해 최소 (인덱스+1) 저장
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int press = i + 1;

                // 존재하면 값 비교, 없으면 넣음
                minPress.put(c, Math.min(minPress.getOrDefault(c, Integer.MAX_VALUE), press));
            }
        }

        // 2) targets 계산
        for (int t = 0; t < targets.length; t++) {
            String target = targets[t];
            int sum = 0;
            boolean possible = true;

            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);

                Integer press = minPress.get(c);
                if (press == null) {     // 해시맵에 없으면 작성 불가
                    possible = false;
                    break;
                }
                sum += press;
            }

            answer[t] = possible ? sum : -1;
        }

        return answer;
    }
}
