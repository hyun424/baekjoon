import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] robot = new int[1000001];
    static int[] res = new int[1000001];
    private static int find(int a){
        if (robot[a] == a) return a;
        return robot[a] = find(robot[a]);

    }
    private static void union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) robot[rb] = ra;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= 1000000; i++){
            robot[i] = i;
        }
        Arrays.fill(res, 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char commend = st.nextToken().charAt(0);
            if (commend == 'I'){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (find(a) != find(b)){
                    res[find(a)] += res[find(b)];
                    union(a, b);
                }

            }
            else if (commend == 'Q'){
                int c = Integer.parseInt(st.nextToken());
                sb.append(res[find(c)]).append('\n');
            }
        }
        System.out.print(sb);
        // int n = Integer.parseInt(br.readLine());
    }
}
