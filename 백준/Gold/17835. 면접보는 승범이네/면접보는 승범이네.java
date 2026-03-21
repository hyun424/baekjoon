import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//면접자는 자기의 도시에서 가장 가까운 면접장으로 감
//이때 면접자가 면접장까지 가는 거리가 제일 먼 도시를 찾음
//도시가 여러곳일때는 그중 번호가 낮은걸 채택
//면접장까지 다익스트라 도는데 한 번에 면접장 여러개 돌려야함 그중에 하나라도 도착하면 끝

public class Main {
    private static final Long INF = Long.MAX_VALUE;
    private static class Node {
        int to;
        long cost;

        Node(int to, long cost){
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> roads = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            roads.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            roads.get(V).add(new Node(U, C));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));

        int[] targets =new int[K];
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            targets[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Node(targets[i], 0));
            dist[targets[i]] = 0;
        }


        while(!pq.isEmpty()){
            Node node = pq.poll();
            int to = node.to;
            long cost = node.cost;
            if (cost > dist[to]) continue;
            for(Node n : roads.get(to)){

                if (dist[n.to] > cost + n.cost) {
                    dist[n.to] = cost + n.cost;
                    pq.offer(new Node(n.to, dist[n.to]));


                }
            }
        }
        long ans = 0;
        int index = 0;
        for (int i = 1; i < N + 1; i++){
            if (dist[i] > ans) {
                ans = dist[i];
                index = i;
            }
        }
        System.out.println(index);
        System.out.println(ans);

    }
}
