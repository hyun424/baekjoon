import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken()); //총 F층까지
        int S = Integer.parseInt(st.nextToken()); //현위치
        int G = Integer.parseInt(st.nextToken()); //목표위치
        int U = Integer.parseInt(st.nextToken()); // U 층 위
        int D = Integer.parseInt(st.nextToken()); // D 층 아래로 이동
        int[] ans = new int[F + 1];
        Arrays.fill(ans, -1);
        ans[S] += 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(S);
        while (!q.isEmpty()) {
            int C = q.poll();
            int nextU = C + U;
            int nextD = C - D;

            if (0 < nextU && nextU < F + 1) {
                if(ans[nextU] == -1) {
                    ans[nextU] = ans[C] + 1;
                    q.add(nextU);
                }
            }



            if (0 < nextD && nextD < F + 1) {
                if(ans[nextD] == -1) {

                    ans[nextD] = ans[C] + 1;
                    q.add(nextD);
                }
            }



        }
        if (ans[G] == -1) {
            System.out.println("use the stairs");
        }
        else{
            System.out.println(ans[G]);
        }
    }
}