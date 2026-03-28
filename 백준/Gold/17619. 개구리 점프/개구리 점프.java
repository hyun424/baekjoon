import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] logs = new int[n][3];
        int[] group = new int[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            logs[i][0] = Integer.parseInt(st.nextToken());
            logs[i][1] = Integer.parseInt(st.nextToken());
            st.nextToken();
            logs[i][2] = i + 1;
        }

        Arrays.sort(logs, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int groupNum = 0;
        int maxRight = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || logs[i][0] > maxRight) {
                groupNum++;
                maxRight = logs[i][1];
            } else {
                maxRight = Math.max(maxRight, logs[i][1]);
            }
            group[logs[i][2]] = groupNum;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (group[a] == group[b]) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
