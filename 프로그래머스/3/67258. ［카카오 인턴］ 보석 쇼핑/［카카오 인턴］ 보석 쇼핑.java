import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int totalType = set.size();

        Map<String, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int bestLeft = 0;
        int bestRight = gems.length - 1;
        int minLen = Integer.MAX_VALUE;

        while (right < gems.length) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            right++;

            while (map.size() == totalType) {
                if (right - left < minLen) {
                    minLen = right - left;
                    bestLeft = left;
                    bestRight = right - 1;
                }

                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }

        return new int[]{bestLeft + 1, bestRight + 1};
    }
}