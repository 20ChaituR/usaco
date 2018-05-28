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

public class Disruption3 {

    int N, M;
    Edge[] addPaths;
    HashMap<Integer, Set<Node>> edges = new HashMap<>();
    int[] len;

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
        int maxlevel = 0;
        int[] level = new int[N];
        int[] degree = new int[N];
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
        L = new int[N];
        int j;
        for (j = 0; 1 << j < N; j++);
        P = new int[N][j];
        parent[r] = -1;
        dfs(r);
        pp();
    }

    void dfs(int root) {
        for (Node e : edges.get(root)) {
            if (parent[e.a] == -2) {
                parent[e.a] = root;
                index[e.a] = e.ind;
                L[e.a] = L[root] + 1;
                dfs(e.a);
            }
        }
    }

    int[][] P;
    int[] L;

    void pp() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; 1 << j < N; j++)
                P[i][j] = -1;
        }
        for (int i = 0; i < N; i++)
            P[i][0] = parent[i];
        for (int j = 1; 1 << j < N; j++)
            for (int i = 0; i < N; i++)
                if (P[i][j - 1] != -1)
                    P[i][j] = P[P[i][j - 1]][j - 1];
    }

    int LCA(int p, int q) {
        int tmp, log, i;
        if (L[p] < L[q]) {
            tmp = p;
            p = q;
            q = tmp;
        }
        for (log = 1; 1 << log <= L[p]; log++);
        log--;
        for (i = log; i >= 0; i--)
            if (L[p] - (1 << i) >= L[q])
                p = P[p][i];
        if (p == q)
            return p;
        for (i = log; i >= 0; i--) {
            if (P[p][i] != -1 && P[p][i] != P[q][i]) {
                p = P[p][i];
                q = P[q][i];
            }
        }
        return parent[p];
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
        Disruption3 prob = new Disruption3();
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
