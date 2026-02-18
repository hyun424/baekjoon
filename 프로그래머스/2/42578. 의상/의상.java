import java.util.*;

class Solution {
    public int solution(String[][] clothes) {

        Map<String, Integer> map = new HashMap<>();

        for (String[] c : clothes) {
            String category = c[1];

            map.put(category, map.getOrDefault(category, 0) + 1);
        }

        int answer = 1;

        // 조합 계산
        for (int count : map.values()) {
            answer *= (count + 1);
        }

        // 아무것도 안 입는 경우 제외
        return answer - 1;
    }
}
