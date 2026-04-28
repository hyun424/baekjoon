import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final long MOD = 1_000_000_007L;
    static final int N = 8;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long D = Long.parseLong(br.readLine());

        long[][] adj = new long[N][N];

        // 0: 정보과학관
        // 1: 전산관
        // 2: 미래관
        // 3: 신양관
        // 4: 한경직기념관
        // 5: 진리관
        // 6: 학생회관
        // 7: 형남공학관

        connect(adj, 0, 1);
        connect(adj, 0, 2);
        connect(adj, 1, 2);
        connect(adj, 1, 3);
        connect(adj, 2, 3);
        connect(adj, 2, 4);
        connect(adj, 3, 4);
        connect(adj, 3, 5);
        connect(adj, 4, 5);
        connect(adj, 4, 7);
        connect(adj, 5, 6);
        connect(adj, 6, 7);

        long[][] result = pow(adj, D);
        System.out.println(result[0][0]);
    }

    static void connect(long[][] adj, int a, int b) {
        adj[a][b] = 1;
        adj[b][a] = 1;
    }

    static long[][] pow(long[][] matrix, long exp) {
        long[][] result = new long[N][N];

        // 단위 행렬
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        long[][] base = matrix;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }

        return result;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < N; j++) {
                    if (b[k][j] == 0) continue;
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}