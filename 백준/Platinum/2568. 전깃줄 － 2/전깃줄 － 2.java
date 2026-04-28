import java.io.*;
import java.util.*;

public class Main {

    static class Wire {
        int a, b;

        Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

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

    static int lowerBound(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        Wire[] wires = new Wire[n];
        for (int i = 0; i < n; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            wires[i] = new Wire(a, b);
        }

        Arrays.sort(wires, Comparator.comparingInt(w -> w.a));

        ArrayList<Integer> lis = new ArrayList<>();
        int[] pos = new int[n]; // 각 전깃줄이 LIS 몇 번째 자리에 들어갔는지

        for (int i = 0; i < n; i++) {
            int b = wires[i].b;
            int idx = lowerBound(lis, b);

            if (idx == lis.size()) {
                lis.add(b);
            } else {
                lis.set(idx, b);
            }

            pos[i] = idx;
        }

        int lisLen = lis.size();
        boolean[] keep = new boolean[n];

        int target = lisLen - 1;
        int lastB = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (pos[i] == target && wires[i].b < lastB) {
                keep[i] = true;
                lastB = wires[i].b;
                target--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n - lisLen).append('\n');

        for (int i = 0; i < n; i++) {
            if (!keep[i]) {
                sb.append(wires[i].a).append('\n');
            }
        }

        System.out.print(sb);
    }
}