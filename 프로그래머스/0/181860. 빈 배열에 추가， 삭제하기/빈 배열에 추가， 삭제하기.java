import java.util.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        List<Integer> x = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (flag[i]) {
                for (int k = 0; k < arr[i] * 2; k++) {
                    x.add(arr[i]);
                }
            } else {
                for (int k = 0; k < arr[i]; k++) {
                    x.remove(x.size() - 1);
                }
            }
        }

        int[] answer = new int[x.size()];
        for (int i = 0; i < x.size(); i++) answer[i] = x.get(i);
        return answer;
    }
}
