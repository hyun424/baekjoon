import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < (1 << 7); i++) {
            String binary = Integer.toBinaryString(i);
            String numStr = binary.replace('1', '5').replace('0', '0');
            int num = Integer.parseInt(numStr);

            if (l <= num && num <= r) {
                list.add(num);
            }
        }

        if (list.isEmpty()) return new int[]{-1};

        Collections.sort(list);

        return list.stream().mapToInt(i -> i).toArray();
    }
}