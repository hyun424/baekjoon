import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][2];
        for (int i = 0; i < n; i ++){
            StringTokenizer st =  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[i][0] = a;
            graph[i][1] = b;
        }

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            sum1 += (long) graph[i][0] * graph[next][1];
            sum2 += (long) graph[i][1] * graph[next][0];
        }

        double answer = Math.abs(sum1 - sum2) / 2.0;
        System.out.printf("%.1f%n", answer);
    }
}
