import java.util.*;

class Solution {
    public int solution(String numbers) {
        char[] arr = numbers.toCharArray();
        boolean[] used = new boolean[arr.length];
        Set<Integer> set = new HashSet<>();

        dfs(arr, used, new StringBuilder(), set);

        int answer = 0;
        for (int x : set) {
            if (isPrime(x)) answer++;
        }
        return answer;
    }

    private void dfs(char[] arr, boolean[] used, StringBuilder sb, Set<Integer> set) {
        if (sb.length() > 0) {
            set.add(Integer.parseInt(sb.toString())); // "011" -> 11로 자동 처리
        }
        if (sb.length() == arr.length) return;

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            sb.append(arr[i]);
            dfs(arr, used, sb, set);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
