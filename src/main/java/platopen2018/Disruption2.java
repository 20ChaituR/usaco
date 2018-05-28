/*
 * Created by cravuri on 3/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Disruption2 {

    int N, M;
    Edge[] addPaths;
    HashMap<Integer, Set<Node>> edges = new HashMap<>();
    int[] len;
    int maxlevel;
    int[] level;
    int[] degree;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("disrupt.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        addPaths = new Edge[M];
        for (int i = 0; i < N; i++) {
            edges.put(i, new HashSet<Node>());
        }
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            edges.get(a).add(new Node(b, i));
            edges.get(b).add(new Node(a, i));
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            addPaths[i] = new Edge(a, b, c);
        }
    }

    int[] parent;
    int[] index;

    void buildTree() {
        parent = new int[N];
        index = new int[N];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -2;
        }
        maxlevel = 0;
        level = new int[N];
        degree = new int[N];
        Queue<Integer> q = new ArrayDeque<>();
        int r = 0;
        for (int i = 0; i < N; i++) {
            degree[i] = edges.get(i).size();
            if (degree[i] == 1) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int v = q.remove();
            for (Node i : edges.get(v)) {
                degree[i.a]--;
                if (degree[i.a] == 1) {
                    q.add(i.a);
                    level[i.a] = level[v] + 1;
                    maxlevel = Math.max(maxlevel, level[i.a]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (level[i] == maxlevel) {
                r = i;
                break;
            }
        }
        parent[r] = -1;
        dfs(r);
        L = new int[N];
        P = new int[N];
        pp(r, (int) Math.sqrt(maxlevel), 0);
    }

    void dfs(int root) {
        for (Node e : edges.get(root)) {
            if (parent[e.a] == -2) {
                parent[e.a] = root;
                index[e.a] = e.ind;
                dfs(e.a);
            }
        }
    }

    int[] P;
    int[] L;

    void pp(int node, int nr, int level) {
        L[node] = level;
        if (level < nr) {
            parent[node] = 1;
        } else {
            if (level % nr == 0) {
                P[node] = parent[node];
            } else {
                P[node] = P[parent[node]];
            }
        }
        for (Node e : edges.get(node)) {
            if (e.a != parent[node]) {
                pp(e.a, nr, level + 1);
            }
        }
    }

    int LCA(int x, int y) {
        while (P[x] != P[y]) {
            if (L[x] > L[y]) {
                x = P[x];
            } else {
                y = P[y];
            }
        }
        while (x != y) {
            if (L[x] > L[y]) {
                x = parent[x];
            } else {
                y = parent[y];
            }
        }
        return x;
    }

    void LCAPath(int a, int b, int c) {
        int lca = LCA(a, b);
        int cur = a;
        while (cur != lca) {
            int ind = index[cur];
            if (len[ind] == -1) {
                len[ind] = c;
            }
            cur = parent[cur];
        }
        cur = b;
        while (cur != lca) {
            int ind = index[cur];
            if (len[ind] == -1) {
                len[ind] = c;
            }
            cur = parent[cur];
        }
    }

    // returns a list of the indices of the edges in the path
    void path(int a, int b, int c) {
        Set<Integer> path = new HashSet<>();
        if (level[a] < level[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int cur = a;
        while (cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }
        int lca = b;
        while (lca != -1) {
            if (path.contains(lca)) {
                break;
            }
            int ind = index[lca];
            if (len[ind] == -1) {
                len[ind] = c;
            }
            lca = parent[lca];
        }
        cur = a;
        while (cur != lca) {
            int ind = index[cur];
            if (len[ind] == -1) {
                len[ind] = c;
            }
            cur = parent[cur];
        }
//        cur = b;
//        while (cur != lca) {
//            int ind = index[cur];
//            if (len[ind] == -1) {
//                len[ind] = c;
//            }
//            cur = parent[cur];
//        }
    }

    void solve() throws IOException {
        buildTree();
        Arrays.sort(addPaths);
        len = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            len[i] = -1;
        }
        for (Edge addPath : addPaths) {
            LCAPath(addPath.a, addPath.b, addPath.c);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("disrupt.out"));
        for (int i = 0; i < N - 1; i++) {
            pw.println(len[i]);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Disruption2 prob = new Disruption2();
        prob.readFile();
        prob.solve();
    }

    class Node {
        int a;
        int ind;

        public Node(int a, int ind) {
            this.a = a;
            this.ind = ind;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a);
        }
    }

    class Edge implements Comparable<Edge> {
        int a;
        int b;
        int c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }
    }

}
