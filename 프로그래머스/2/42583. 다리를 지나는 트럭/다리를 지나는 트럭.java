import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>();
        int time = 0;
        int sum = 0; // 현재 다리 위 총 무게
        int idx = 0;

        // 초기 다리: 전부 0으로 채워서 길이 맞추기
        for (int i = 0; i < bridge_length; i++) bridge.add(0);

        while (!bridge.isEmpty()) {
            time++;

            // 1) 한 칸 이동: 맨 앞 제거(내려감)
            int out = bridge.poll();
            sum -= out;

            // 2) 다음 트럭 올릴지 결정
            if (idx < truck_weights.length) {
                int next = truck_weights[idx];
                if (sum + next <= weight) {
                    bridge.add(next);
                    sum += next;
                    idx++;
                } else {
                    bridge.add(0);
                }
            }
            // 3) 더 올릴 트럭이 없으면 0만 넣으면서 비워질 때까지 진행
            else {
                // 남은 트럭이 없으면 그냥 다리 비우기
                // (out은 이미 빠졌으니) 뒤에는 0만 채움
                if (sum == 0) break; // 다리 위가 비었으면 종료 가능
                bridge.add(0);
            }
        }

        return time;
    }
}
