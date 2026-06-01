import java.util.*;

class Solution {
    public int solution(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int n : nums) {
            set.add(n);
        }

        int maxPick = nums.length / 2;
        int typeCount = set.size();

        return Math.min(typeCount, maxPick);
    }
}