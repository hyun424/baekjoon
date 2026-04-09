import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] names = new String[n];
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, ArrayList<String>> adj = new HashMap<>();
        Map<String, ArrayList<String>> child = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            names[i] = st.nextToken();
            indegree.put(names[i], 0);
            adj.put(names[i], new ArrayList<>());
            child.put(names[i], new ArrayList<>());
        }

        Arrays.sort(names);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();
            String y = st.nextToken();

            adj.get(y).add(x);
            indegree.put(x, indegree.get(x) + 1);
        }

        PriorityQueue<String> q = new PriorityQueue<>();
        List<String> roots = new ArrayList<>();

        for (String name : names) {
            if (indegree.get(name) == 0) {
                q.offer(name);
                roots.add(name);
            }
        }

        while (!q.isEmpty()) {
            String cur = q.poll();

            for (String next : adj.get(cur)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    child.get(cur).add(next);
                    q.offer(next);
                }
            }
        }

        for (String name : names) {
            child.get(name).sort(String::compareTo);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append('\n');
        for (int i = 0; i < roots.size(); i++) {
            sb.append(roots.get(i));
            if (i + 1 < roots.size()) sb.append(' ');
        }
        sb.append('\n');

        for (String name : names) {
            List<String> children = child.get(name);
            sb.append(name).append(' ').append(children.size());
            for (String next : children) {
                sb.append(' ').append(next);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
