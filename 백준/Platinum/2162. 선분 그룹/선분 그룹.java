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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static class Segment {
        long x1, y1, x2, y2;

        Segment(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static int[] parent;
    static int[] size;
    static Segment[] segs;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;

        if (size[ra] < size[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }

        parent[rb] = ra;
        size[ra] += size[rb];
    }

    static long ccw(long ax, long ay, long bx, long by, long cx, long cy) {
        long cross = (bx - ax) * (cy - ay) - (by - ay) * (cx - ax);
        if (cross > 0) return 1;
        if (cross < 0) return -1;
        return 0;
    }

    static boolean overlap1D(long a, long b, long c, long d) {
        return Math.max(Math.min(a, b), Math.min(c, d)) <=
               Math.min(Math.max(a, b), Math.max(c, d));
    }

    static boolean isIntersect(Segment s1, Segment s2) {
        long ab1 = ccw(s1.x1, s1.y1, s1.x2, s1.y2, s2.x1, s2.y1);
        long ab2 = ccw(s1.x1, s1.y1, s1.x2, s1.y2, s2.x2, s2.y2);
        long cd1 = ccw(s2.x1, s2.y1, s2.x2, s2.y2, s1.x1, s1.y1);
        long cd2 = ccw(s2.x1, s2.y1, s2.x2, s2.y2, s1.x2, s1.y2);

        // 네 점이 모두 한 직선 위에 있는 경우
        if (ab1 == 0 && ab2 == 0 && cd1 == 0 && cd2 == 0) {
            return overlap1D(s1.x1, s1.x2, s2.x1, s2.x2) &&
                   overlap1D(s1.y1, s1.y2, s2.y1, s2.y2);
        }

        return (ab1 * ab2 <= 0) && (cd1 * cd2 <= 0);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        segs = new Segment[n];
        for (int i = 0; i < n; i++) {
            long x1 = fs.nextLong();
            long y1 = fs.nextLong();
            long x2 = fs.nextLong();
            long y2 = fs.nextLong();
            segs[i] = new Segment(x1, y1, x2, y2);
        }

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isIntersect(segs[i], segs[j])) {
                    union(i, j);
                }
            }
        }

        int groupCount = 0;
        int maxGroupSize = 0;

        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                groupCount++;
                maxGroupSize = Math.max(maxGroupSize, size[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(groupCount).append('\n');
        sb.append(maxGroupSize).append('\n');
        System.out.print(sb);
    }
}