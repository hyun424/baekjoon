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

        if (parent == remove) return;
        if (graph.get(parent).isEmpty()) {
            res += 1;
            return;
        }
        if(graph.get(parent).size() == 1 && graph.get(parent).get(0)==remove){
            res += 1;
            return;
        }
        for (int child : graph.get(parent)) {
            leafnode(child);
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
