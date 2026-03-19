import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


public class Main {

    static int[][] moves = {new int[]{1, 1}, new int[] {1, -1}, new int[] {2, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //locate[][] 현위치, {time, 이전위치}
        int[][] locate = new int[200000][2];
        for (int i = 0; i < locate.length; i++) {
            locate[i][0] = -1;
            locate[i][1] = -1;
        }
        locate[N] = new int[] {0, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{N, 0, -1}); // 현 위치 time 이전 위치
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int c = cur[0];
            int t = cur[1];
            int prev = cur[2];
            if (c == K) break;// 종료 조건
            for (int[] move : moves) {
                int next = move[0] * c + move[1];
                if (0 <= next && next < 200000 && locate[next][0] == -1){
                    queue.offer(new int[] {next, t + 1, c});
                    locate[next] = new int[] {t + 1, c};
                }
            }

        }

        printAns(locate, K);

    }
    private static void printAns(int[][] locate, int K){
        if (locate[K][0] == -1) {
            System.out.println("error");
            return;
        }
        System.out.println(locate[K][0]);
        ArrayList<Integer> path = new ArrayList<>();
        int cur = K;

        while (cur != -1) {
            path.add(cur);
            cur = locate[cur][1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i)).append(' ');
        }
        System.out.println(sb);
        return;
    }
}
