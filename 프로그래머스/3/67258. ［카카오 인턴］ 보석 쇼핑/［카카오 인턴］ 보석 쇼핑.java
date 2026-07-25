import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int kindCount = new HashSet<>(Arrays.asList(gems)).size();

        Map<String, Integer> map = new HashMap<>();

        int start = 0;
        int bestStart = 0;
        int bestEnd = gems.length - 1;

        for (int end = 0; end < gems.length; end++) {
            // 오른쪽 보석 추가
            map.put(
                gems[end],
                map.getOrDefault(gems[end], 0) + 1
            );

            // 모든 종류가 들어 있다면 왼쪽을 최대한 줄임
            while (map.size() == kindCount) {
                // 지금까지 찾은 구간보다 짧으면 갱신
                if (end - start < bestEnd - bestStart) {
                    bestStart = start;
                    bestEnd = end;
                }

                // 왼쪽 보석 제거
                String startGem = gems[start];
                map.put(startGem, map.get(startGem) - 1);

                // 개수가 0이면 현재 구간에 없는 종류이므로 삭제
                if (map.get(startGem) == 0) {
                    map.remove(startGem);
                }

                start++;
            }
        }

        // 배열 인덱스는 0부터, 진열대 번호는 1부터
        return new int[]{bestStart + 1, bestEnd + 1};
    }
}