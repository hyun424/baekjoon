import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] board;
    static boolean[] diag1;
    static boolean[] diag2;
    static ArrayList<int[]> black = new ArrayList<>();
    static ArrayList<int[]> white = new ArrayList<>();

    static int dfs(int idx, ArrayList<int[]> cells) {
        if (idx == cells.size()) return 0;

        int r = cells.get(idx)[0];
        int c = cells.get(idx)[1];

        int result = dfs(idx + 1, cells);

        int d1 = r + c;
        int d2 = r - c + n - 1;
        if (!diag1[d1] && !diag2[d2]) {
            diag1[d1] = true;
            diag2[d2] = true;
            result = Math.max(result, 1 + dfs(idx + 1, cells));
            diag1[d1] = false;
            diag2[d2] = false;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    if ((i + j) % 2 == 0) {
                        black.add(new int[] {i, j});
                    } else {
                        white.add(new int[] {i, j});
                    }
                }
            }
        }

        int answer = dfs(0, black) + dfs(0, white);
        System.out.println(answer);
    }
}
