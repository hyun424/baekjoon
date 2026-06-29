import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for (int l : lost) {
            lostSet.add(l);
        }

        for (int r : reserve) {
            reserveSet.add(r);
        }

        // 여벌이 있지만 도난당한 학생 처리
        for (int r : reserve) {
            if (lostSet.contains(r)) {
                lostSet.remove(r);
                reserveSet.remove(r);
            }
        }

        int answer = n - lostSet.size();

        List<Integer> lostList = new ArrayList<>(lostSet);
        Collections.sort(lostList);

        for (int l : lostList) {
            if (reserveSet.contains(l - 1)) {
                reserveSet.remove(l - 1);
                answer++;
            } else if (reserveSet.contains(l + 1)) {
                reserveSet.remove(l + 1);
                answer++;
            }
        }

        return answer;
    }
}