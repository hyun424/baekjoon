import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> score = new HashMap<>();

        for (char c : new char[]{'R','T','C','F','J','M','A','N'}) {
            score.put(c, 0);
        }

        for (int i = 0; i < survey.length; i++) {
            char disagree = survey[i].charAt(0);
            char agree = survey[i].charAt(1);
            int choice = choices[i];

            if (choice < 4) {
                score.put(disagree, score.get(disagree) + (4 - choice));
            } else if (choice > 4) {
                score.put(agree, score.get(agree) + (choice - 4));
            }
        }

        StringBuilder answer = new StringBuilder();

        answer.append(score.get('R') >= score.get('T') ? 'R' : 'T');
        answer.append(score.get('C') >= score.get('F') ? 'C' : 'F');
        answer.append(score.get('J') >= score.get('M') ? 'J' : 'M');
        answer.append(score.get('A') >= score.get('N') ? 'A' : 'N');

        return answer.toString();
    }
}