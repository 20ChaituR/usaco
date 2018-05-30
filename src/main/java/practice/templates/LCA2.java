/*
 * Created by cravuri on 5/29/18
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LCA2 {

    int N;
    List<int[]> edges = new ArrayList<>();
    List<List<Integer>> children = new ArrayList<>();

    void readFile() {
        /*
        sample input
        5
        1 2
        1 3
        2 4
        2 5
         */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            edges.add(new int[] {a, b});
        }
        makeTree();
    }

    List<Integer> eulerTour = new ArrayList<>();
    int[] level;

    void tour(int u) {
        eulerTour.add(u);
        for (int v : children.get(u)) {
            pos[v] = eulerTour.size();
            tour(v);
            level[v] = 1 + level[u];
            eulerTour.add(u);
        }
    }

    int[][] P;
    int[] pos;

    void precomp() {
        pos = new int[N];
        tour(0);
        int M = eulerTour.size();
        int lg;
        for (lg = 1; 1 << lg <= M; lg++);
        P = new int[M][lg];
        for (int i = 0; i < M; i++) {
            P[i][0] = eulerTour.get(i);
        }
        for (int j = 1; j < P[0].length; j++) {
            for (int i = 0; i+1<<j < M; i++) {
                if (level[P[i][j-1]] < level[P[i+1<<(j-1)][j-1]]) {
                    P[i][j] = P[i][j-1];
                } else {
                    P[i][j] = P[i+1<<(j-1)][j-1];
                }
            }
        }
    }

    int lca(int u, int v) {
        u--;v--;
        int a = pos[u];
        int b = pos[v];
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        int lg;
        for (lg = 1; 1 << lg <= b - a; lg++);
        int min1 = P[a][lg];
        int min2 = P[b-1<<lg][lg];
        if (level[min1] < level[min2]) {
            return min1;
        } else {
            return min2;
        }
    }

    void solve() {
        precomp();
        System.out.println(lca(4, 5));
    }

    public static void main(String[] args) {
        LCA2 prob = new LCA2();
        prob.readFile();
        prob.solve();
    }

    void makeTree() {
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet(i);
        }
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        for (int i = 0; i < N; i++) {
            children.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N; i++) {
            if (parent[i] != i) {
                children.get(parent[i]).add(i);
            }
        }
    }

    int[] parent;
    int[] rank;

    void makeSet(int x) {
        parent[x] = x;
        rank[x] = 0;
    }

    int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b) {
            return;
        }
        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else if (rank[b] < rank[a]) {
            parent[b] = a;
        } else {
            parent[a] = b;
            rank[b]++;
        }
    }

}
