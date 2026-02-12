import java.util.*;

class Solution {
    public int[] solution(int[] arr, int k) {
        int[] answer = new int[k];
        Set<Integer> set = new HashSet<>();
        
        int idx = 0;
        
        for (int num : arr) {
            if (!set.contains(num)) {
                set.add(num);
                answer[idx++] = num;
                
                if (idx == k) break;
            }
        }
        
        // 부족한 부분 -1 채우기
        while (idx < k) {
            answer[idx++] = -1;
        }
        
        return answer;
    }
}
