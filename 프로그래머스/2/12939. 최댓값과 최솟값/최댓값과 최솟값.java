import java.util.*;

class Solution {
    public String solution(String s) {
        String[] words = s.split(" ");

        int[] nums = Arrays.stream(words)
                           .mapToInt(str -> Integer.parseInt(str))
                           .toArray();

        int min = Arrays.stream(nums)
                        .min()
                        .getAsInt();

        int max = Arrays.stream(nums)
                        .max()
                        .getAsInt();

        return min + " " + max;
    }
}