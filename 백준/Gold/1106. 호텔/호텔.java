import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] city = new int[n][2];
        int maxCustomer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            city[i][0] = Integer.parseInt(st.nextToken());
            city[i][1] = Integer.parseInt(st.nextToken());
            maxCustomer = Math.max(maxCustomer, city[i][1]);
        }

        int limit = c + maxCustomer;
        int[] dp = new int[limit + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int cost = city[i][0];
            int customer = city[i][1];
            for (int j = customer; j <= limit; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }
        }

        int answer = 1_000_000_000;
        for (int i = c; i <= limit; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
