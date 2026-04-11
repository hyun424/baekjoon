import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int N = fs.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = fs.nextInt();
        }

        int[] lis = new int[N];
        int[] pos = new int[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            int idx = lowerBound(lis, 0, len, arr[i]);
            lis[idx] = arr[i];
            pos[i] = idx;

            if (idx == len) len++;
        }

        int[] answer = new int[len];
        int target = len - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == target) {
                answer[target] = arr[i];
                target--;
            }
            if (target < 0) break;
        }

        sb.append(len).append('\n');
        for (int i = 0; i < len; i++) {
            sb.append(answer[i]).append(' ');
        }

        System.out.print(sb);
    }

    static int lowerBound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}