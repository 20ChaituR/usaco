/*
 * Created by cravuri on 5/29/18
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Makes a tree from a list of edges
 */
public class MakeTree {

    int N, M;
    List<int[]> edges = new ArrayList<>();

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt(); // or M = N - 1;
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            // int c = sc.nextInt();
            edges.add(new int[] {a, b}); // {a, b, c}
        }
    }

    List<List<Integer>> children = new ArrayList<>();

    void makeTree() {
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
        return find(x);
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
