import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();

        int len = arr[0].length();

        for (int i = 0; i < len; i++) {
            char c = arr[0].charAt(i);
            boolean same = true;

            for (int j = 1; j < n; j++) {
                if (arr[j].charAt(i) != c) {
                    same = false;
                    break;
                }
            }

            if (same) sb.append(c);
            else sb.append('?');
        }

        System.out.println(sb);
    }
}