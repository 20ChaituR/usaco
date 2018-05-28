/*
 * Created by cravuri on 3/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Disruption {

    int N, M;
    Edge[] addPaths;
    HashMap<Integer, Set<Node>> edges = new HashMap<>();

//    List<Edge> edges2 = new ArrayList<>();

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
//        for (int i = 0; i < N - 1; i++) {
//            int a = sc.nextInt() - 1;
//            int b = sc.nextInt() - 1;
//            edges2.add(new Edge(a, b, i));
//        }
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
        // mb calculate center and go from there if too slow
        parent[0] = -1;
        dfs(0);
    }

    void dfs(int root) {
//        int max = 0;
//        for (int ind : edges.keySet()) {
//            if (edges.get(ind).size() > edges.get(max).size()) {
//                max = ind;
//            }
//        }
        for (Node e : edges.get(root)) {
            if (parent[e.a] == -2) {
                parent[e.a] = root;
                index[e.a] = e.ind;
                dfs(e.a);
            }
        }
    }

    // returns a list of the indices of the edges in the path
    List<Integer> path(int a, int b) {
        Set<Integer> pathA = new HashSet<>();
        int cur = a;
        while (cur != -1) {
            pathA.add(cur);
            cur = parent[cur];
        }
        int lca = b;
        while (lca != -1) {
            if (pathA.contains(lca)) {
                break;
            }
            lca = parent[lca];
        }
        List<Integer> path = new ArrayList<>();
        cur = a;
        while (cur != lca) {
            path.add(index[cur]);
            cur = parent[cur];
        }
        cur = b;
        while (cur != lca) {
            path.add(index[cur]);
            cur = parent[cur];
        }
        return path;
    }

    void solve() throws IOException {
        buildTree();
        Arrays.sort(addPaths);
        int[] len = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            len[i] = -1;
        }
        for (int i = 0; i < addPaths.length; i++) {
            List<Integer> p = path(addPaths[i].a, addPaths[i].b);
            for (Integer ind : p) {
                if (len[ind] == -1) {
                    len[ind] = addPaths[i].c;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("disrupt.out"));
        for (int i = 0; i < N - 1; i++) {
            pw.println(len[i]);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Disruption prob = new Disruption();
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
    }

    class Edge implements Comparable<Edge> {
        int a;
        int b;
        int c; // index or cost

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
