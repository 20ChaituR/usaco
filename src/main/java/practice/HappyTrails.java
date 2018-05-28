import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by usaco15 on 5/26/18.
 */
public class HappyTrails {

    int N, M, K;

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            edges.add(new int[]{a, b, c});
        }
    }

    int[] parent;
    int[] rank;

    int findAncestor(int a) {
        if (parent[a] == a) {
            return a;
        }
        int p = parent[a];
        return findAncestor(p);
    }

    void union(int x, int y) {
        int a = findAncestor(x);
        int b = findAncestor(y);
        if (a == b) {
            return;
        }
        if (rank[a] > rank[b]) {
            parent[b] = a;
            rank[a] += rank[b];
        } else {
            parent[a] = b;
            rank[b] += rank[a];
        }
    }

    void makeSet(int a) {
        parent[a] = a;
        rank[a] = 1;
    }

    boolean[] used;
    PriorityQueue<Integer> costs = new PriorityQueue<>();
    int num = 0;

    void costSpanning(int i, int cost) {
        if (num >= K && cost > costs.peek()) {
            return;
        }

        if (i >= edges.size()) {
            // check if all are in the same set
            // if so add cost to costs
            if (rank[findAncestor(0)] != N) {
                return;
            }
//            for (int j = 1; j < N; j++) {
//                if (findAncestor(j) != a) {
//                    connected = false;
//                    break;
//                }
//            }
            if (num >= K) {
                costs.remove();
            }
            costs.add(cost);
            num++;
            return;
        }

        int[] edge = edges.get(i);
        int a = findAncestor(edge[0]);
        int b = findAncestor(edge[1]);
        if (a == b) {
            costSpanning(i + 1, cost);
        } else {
            costSpanning(i + 1, cost);
            used[i] = true;
            union(a, b);
            costSpanning(i + 1, cost + edge[2]);
            if (parent[a] == b) {
                parent[a] = a;
                rank[b] -= rank[a];
            } else {
                parent[b] = b;
                rank[a] -= rank[b];
            }
        }
    }

    List<int[]> edges = new ArrayList<>();

    void solve() {
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet(i);
        }
        used = new boolean[edges.size()];
        costSpanning(0, 0);
        List<Integer> sorted = new ArrayList<>(costs);
        Collections.sort(sorted);
        if (K - 1 >= sorted.size()) {
            System.out.println(-1);
        } else {
            System.out.println(sorted.get(K - 1));
        }
    }

    public static void main(String[] args) {
        HappyTrails prob = new HappyTrails();
        prob.readFile();
        prob.solve();
    }
}