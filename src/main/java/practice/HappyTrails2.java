/*
 * Created by cravuri on 5/26/18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HappyTrails2 {

    int N, M, K;
    List<int[]> edges = new ArrayList<>();

    void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a, b, c});
        }
    }

    int[] parent;
    int[] rank;
    int[] size;

    int findAncestor(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = findAncestor(parent[a]);
    }

    void union(int x, int y) {
        int a = findAncestor(x);
        int b = findAncestor(y);
        if (a == b) {
            return;
        }
        if (rank[a] > rank[b]) {
            parent[b] = a;
            size[a] += size[b];
        } else if (rank[b] < rank[a]) {
            parent[a] = b;
            size[b] += size[a];
        } else {
            parent[a] = b;
            rank[b]++;
            size[b] += size[a];
        }
    }

    void makeSet(int a) {
        parent[a] = a;
        rank[a] = 1;
        size[a] = 1;
    }

    Pair MST(int[] state) {
        int[] newState = new int[state.length];
        int cost = 0;
        parent = new int[N];
        rank = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet(i);
        }

        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1) {
                int[] edge = edges.get(i);
                union(edge[0], edge[1]);
                newState[i] = 1;
                cost += edge[2];
            } else if (state[i] == -1) {
                newState[i] = -1;
            }
        }

        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                int[] edge = edges.get(i);
                int a = findAncestor(edge[0]);
                int b = findAncestor(edge[1]);
                if (a == b) {
                    newState[i] = 0;
                } else {
                    newState[i] = 1;
                    cost += edge[2];
                    union(a, b);
                }
            }
        }

        if (size[findAncestor(0)] != N) {
            return null;
        }
        return new Pair(cost, newState);
    }

    void solve() {
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        Pair mst = MST(new int[M]);
        if (mst == null) {
            System.out.println(-1);
            return;
        }

        PriorityQueue<Pair> smallest = new PriorityQueue<>();
        smallest.add(mst);
        int k = 0;

        int[] kth = new int[K];
        for (int i = 0; i < K; i++) {
            kth[i] = -1;
        }

        while (!smallest.isEmpty() && k < K) {
            Pair cur = smallest.remove();
            kth[k] = cur.cost;
            int[] state = new int[M];
            System.arraycopy(cur.state, 0, state, 0, cur.change + 1);
            for (int i = cur.change + 1; i < M; i++) {
                if (cur.state[i] == 1) {
                    state[i] = -1;
                    Pair st = MST(state);
                    if (st == null) {
                        state[i] = 1;
                        continue;
                    }
                    st.change = i;
                    smallest.add(st);
                    state[i] = 1;
                }
            }
            k++;
        }

        System.out.println(kth[K - 1]);
    }

    public static void main(String[] args) throws IOException {
        HappyTrails2 prob = new HappyTrails2();
        prob.readFile();
        prob.solve();
    }

    class Pair implements Comparable<Pair> {
        int cost;
        int change;
        int[] state;

        public Pair(int cost, int[] state) {
            this.cost = cost;
            this.state = state;
            this.change = -1;
        }

        @Override
        public int compareTo(Pair o) {
            return cost - o.cost;
        }
    }

}
