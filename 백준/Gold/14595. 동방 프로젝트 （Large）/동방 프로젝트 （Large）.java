import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] range = new int[m][2];
        StringTokenizer st;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            range[i][0] = Integer.parseInt(st.nextToken());
            range[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(range, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int answer = n;
        int right = 0;

        for (int i = 0; i < m; i++) {
            int x = range[i][0];
            int y = range[i][1];

            if (x > right) {
                answer -= (y - x);
                right = y;
            } else if (y > right) {
                answer -= (y - right);
                right = y;
            }
        }

        System.out.println(answer);
    }
}
