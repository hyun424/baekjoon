import java.util.*;

class Solution {

    public String[] solution(String[] orders, int[] course) {

        // course 길이들 빠른 체크용
        boolean[] need = new boolean[11];
        for (int c : course) need[c] = true;

        // 길이별 조합 카운트: counts[len].get(combo) = freq
        @SuppressWarnings("unchecked")
        Map<String, Integer>[] counts = new HashMap[11];
        for (int i = 0; i <= 10; i++) counts[i] = new HashMap<>();

        // 1) 각 주문에서 가능한 조합을 만들어 카운팅
        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);

            // 주문 길이 <= 10 이므로, 필요한 길이만 DFS로 생성
            for (int len : course) {
                if (len <= arr.length) {
                    dfsCount(arr, 0, len, new StringBuilder(), counts[len]);
                }
            }
        }

        // 2) 길이별 최댓값(>=2) 조합들을 모아서 결과 생성
        List<String> result = new ArrayList<>();

        for (int len : course) {
            Map<String, Integer> map = counts[len];

            int max = 0;
            for (int v : map.values()) {
                if (v >= 2) max = Math.max(max, v);
            }
            if (max < 2) continue;

            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() == max) result.add(e.getKey());
            }
        }

        // 3) 사전순 정렬 후 반환
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    // arr에서 길이 targetLen 조합 생성, map에 카운트 누적
    private void dfsCount(char[] arr, int start, int targetLen, StringBuilder sb,
                          Map<String, Integer> map) {

        if (sb.length() == targetLen) {
            String combo = sb.toString();
            map.put(combo, map.getOrDefault(combo, 0) + 1);
            return;
        }

        // 남은 글자 수로 targetLen 채울 수 없으면 종료(가지치기)
        int need = targetLen - sb.length();
        if (arr.length - start < need) return;

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            dfsCount(arr, i + 1, targetLen, sb, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}