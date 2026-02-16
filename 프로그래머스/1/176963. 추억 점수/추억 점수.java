import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> yearnRate = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            yearnRate.put(name[i], yearning[i]);
        }

        for (int j = 0; j < photo.length; j++) {
            int sum = 0;
            for (String person : photo[j]) {
                sum += yearnRate.getOrDefault(person, 0); // 없으면 0점
            }
            answer[j] = sum;
        }

        return answer;
    }
}
