import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] a = new int[n][2];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = i;
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        for(int i = 0; i < n; i++){
            p[a[i][1]] = i;
        }
        for(int i = 0; i < n; i++){
            System.out.print(p[i] + " ");
        }
    }
}