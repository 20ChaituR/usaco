import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra2 {

    static int N;
    static int S;
    static List<Pair>[] adj;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        N = Integer.parseInt(st.nextToken());
        adj = new List[N];
        int E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Pair(b, c));
            adj[b].add(new Pair(a, c));
        }
    }

    public static void dijkstra() {
        int[] dis = new int[N];
        for (int i = 0; i < N; i++) {
            dis[i] = -1;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[N];
        pq.add(new Pair(0, S));
        int count = 0;
        while (count < N && !pq.isEmpty()) {
            Pair next = pq.remove();
            if (vis[next.second]) {
                continue;
            }
            dis[next.second] = next.first;
            vis[next.second] = true;
            for (int i = 0; i < adj[next.second].size(); i++) {
                pq.add(new Pair(adj[next.second].get(i).second + next.first, adj[next.second].get(i).first));
            }
            count++;
        }
        for (int i = 0; i < dis.length; i++) {
            System.out.println(dis[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        readFile();
        dijkstra();
    }

    static class Pair implements Comparator<Pair>, Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return o.first == first ? second - o.second : first - o.first;
        }

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.first == o2.first ? o1.second - o2.second : o1.first - o2.first;
        }
    }

}
