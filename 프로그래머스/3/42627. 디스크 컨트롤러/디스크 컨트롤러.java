import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        int n = jobs.length;

        // {작업번호, 요청시각, 소요시간}
        int[][] tasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = i;
            tasks[i][1] = jobs[i][0];
            tasks[i][2] = jobs[i][1];
        }

        // 요청 시각 순 정렬
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);

        // 소요시간 -> 요청시각 -> 작업번호
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[2] != b[2]) return a[2] - b[2];
                if (a[1] != b[1]) return a[1] - b[1];
                return a[0] - b[0];
            }
        );

        int idx = 0;
        int completed = 0;
        int time = 0;
        long totalTurnaround = 0;

        while (completed < n) {

            // 현재 시각까지 들어온 작업들을 PQ에 추가
            while (idx < n && tasks[idx][1] <= time) {
                pq.offer(tasks[idx++]);
            }

            // 아직 도착한 작업이 없으면 다음 요청 시각으로 점프
            if (pq.isEmpty()) {
                time = tasks[idx][1];
                continue;
            }

            int[] cur = pq.poll();

            int requestTime = cur[1];
            int duration = cur[2];

            time += duration;

            // 반환시간 = 종료시각 - 요청시각
            totalTurnaround += (time - requestTime);

            completed++;
        }

        return (int)(totalTurnaround / n);
    }
}