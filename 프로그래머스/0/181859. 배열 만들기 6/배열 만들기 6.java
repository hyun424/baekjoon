import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> stk = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stk.isEmpty()) {
                stk.add(arr[i]);
            } else {
                int last = stk.get(stk.size() - 1);

                if (last == arr[i]) {
                    stk.remove(stk.size() - 1);
                } else {
                    stk.add(arr[i]);
                }
            }
        }

        if (stk.isEmpty()) {
            return new int[]{-1};
        }

        return stk.stream().mapToInt(Integer::intValue).toArray();
    }
}
