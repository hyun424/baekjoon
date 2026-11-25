import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // 가장 짧은 길이부터 차례대로 확인
        for (int length = l; length <= 100; length++) {
            // 0 + 1 + 2 + ... + (length - 1)
            int offsetSum = length * (length - 1) / 2;

            // 시작 숫자가 음수가 되어야 하므로 이 길이부터는 불가능
            if (offsetSum > n) {
                break;
            }

            // 시작 숫자가 정수가 되는지 확인
            if ((n - offsetSum) % length == 0) {
                int start = (n - offsetSum) / length;

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < length; i++) {
                    sb.append(start + i).append(" ");
                }

                System.out.println(sb);
                return;
            }
        }

        // 길이 100 이하에서 가능한 수열을 찾지 못한 경우
        System.out.println(-1);
    }
}