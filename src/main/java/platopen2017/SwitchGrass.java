import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SwitchGrass {

    int N, M, K, Q;
    List<Pair>[] adj;
    int[] grass;
    int[][] updates;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("grass.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        Q = sc.nextInt();
        adj = new List[N];
        grass = new int[N];
        updates = new int[Q][2];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            adj[a].add(new Pair(b, c));
            adj[b].add(new Pair(a, c));
        }
        for (int i = 0; i < N; i++) {
            grass[i] = sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt();
            updates[i] = new int[] {a, b};
        }
    }

    int[] dijkstra(int A) {
        int[] dis = new int[N];
        for (int i = 0; i < N; i++) {
            dis[i] = -1;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[N];
        pq.add(new Pair(0, A));
        int count = 0;
        while (count < N && !pq.isEmpty()) {
            Pair next = pq.remove();
            if (vis[next.second]) {
                continue;
            }
            dis[next.second] = next.first;
            vis[next.second] = true;
            if (grass[next.second] != grass[A]) {
                return new int[] {dis[next.second], next.second};
            }
            for (int i = 0; i < adj[next.second].size(); i++) {
                pq.add(new Pair(adj[next.second].get(i).second + next.first, adj[next.second].get(i).first));
            }
            count++;
        }
        return new int[] {-1, -1};
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("grass.out"));
        int min = Integer.MAX_VALUE;
        int k1 = -1;
        int k2 = -1;
        for (int i = 0; i < N; i++) {
            int[] a = dijkstra(i);
            if (a[0] < min) {
                min = a[0];
                k1 = i;
                k2 = a[1];
            }
        }
        for (int q = 0; q < updates.length; q++) {
            grass[updates[q][0]] = updates[q][1];
            int[] a = dijkstra(updates[q][0]);
            if (!(updates[q][0] == k1 || updates[q][0] == k2 || a[1] == k1 || a[1] == k2)) {
                if (a[0] < min) {
                    k1 = updates[q][0];
                    k2 = a[1];
                    min = a[0];
                }
            } else {
                k1 = updates[q][0];
                k2 = a[1];
                min = a[0];
            }
            pw.println(min);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        SwitchGrass prob = new SwitchGrass();
        prob.readFile();
        prob.solve();
    }

    class Pair implements Comparator<Pair>, Comparable<Pair> {
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
