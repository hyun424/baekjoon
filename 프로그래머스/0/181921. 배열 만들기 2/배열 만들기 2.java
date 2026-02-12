import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 1; ; i++) {
            String binary = Integer.toBinaryString(i);
            String numberStr = binary.replace('1', '5');
            
            int number = Integer.parseInt(numberStr);
            
            if (number > r) break;
            
            if (number >= l) {
                list.add(number);
            }
        }
        
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
