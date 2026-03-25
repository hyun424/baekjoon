import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Main {
    static int res = 0;
    static int remove;
    static int root;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    static void leafnode(int parent){
        int childCount = 0;

        for (int child : graph.get(parent)) {
            if (child == remove) continue;
            childCount++;
            leafnode(child);
        }

        if (childCount == 0) {
            res += 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int parent;
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            parent = Integer.parseInt(st.nextToken());
            if (parent == -1){
                root = i;
            }else {
                graph.get(parent).add(i);
            }
        }
        st = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(st.nextToken());

        if (root == remove) {
            System.out.println(0);
            return;
        }

        leafnode(root);
        System.out.println(res);
    }
}
