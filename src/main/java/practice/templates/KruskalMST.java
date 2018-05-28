/*
 * Created by cravuri on 1/28/18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class KruskalMST {

    int N;
    List<int[]> edges;

    void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        N = Integer.parseInt(st.nextToken());
        parent = new int[N];
        rank = new int[N];
        edges = new ArrayList<>();
        int x = Integer.parseInt(st.nextToken());
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a, b, c});
        }
    }

    int[] parent, rank;

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    void solve() {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for (int[] edge : edges) {
            if (find(edge[0]) != find(edge[1])) {
                cost += edge[2];
                union(edge[0], edge[1]);
            }
        }
        System.out.println(cost);
    }

    public static void main(String[] args) throws IOException {
        KruskalMST prob = new KruskalMST();
        prob.readFile();
        prob.solve();
    }

}
