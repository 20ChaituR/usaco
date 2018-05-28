/*
 * Created by cravuri on 5/27/18
 */

import java.util.ArrayList;
import java.util.List;

public class HeavyLightDecomposition {

    // given
    List<List<Integer>> children = new ArrayList<>();
    int[] sizes;
    int[] parent;

    void dfs(int u) {
        for (int v : children.get(u)) {
            parent[v] = u;
            dfs(v);
            sizes[u] += sizes[v];
        }
    }

    // what we want to make
    List<List<Integer>> paths = new ArrayList<>();
    List<SegmentTree> seg = new ArrayList<>();
    int[] pathOfNode;

    void getPaths(int u) {
        List<Integer> heavyPath = new ArrayList<>();
        int cur = u;
        heavyPath.add(cur);
        int maxSize = 0;
        while (!children.get(cur).isEmpty()) {
            int maxInd = children.get(cur).get(0);
            for (int v : children.get(cur)) {
                if (sizes[v] > sizes[maxInd]) {
                    maxInd = v;
                }
                if (cur == u && sizes[v] > maxSize) {
                    maxSize = sizes[v];
                }
            }
            cur = maxInd;
            heavyPath.add(cur);
            pathOfNode[cur] = paths.size();
        }

        paths.add(heavyPath);
        seg.add(new SegmentTree(heavyPath));

        for (int v : children.get(u)) {
            if (sizes[v] != maxSize) {
                getPaths(v);
            }
        }
    }

    void update(int u, int v, int x) {
        
    }

    void query(int u, int v) {

    }

    class SegmentTree {
        int N;
        int[] arr;
        int[] lazy;
        int[] seg;

        public SegmentTree(List<Integer> list) {
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = list.get(i);
            }

            lazy = new int[4 * N + 1];
            seg = new int[4 * N + 1];

            buildTree(1, 1, N);
        }

        private void buildTree(int node, int l, int r) {
            if (node >= seg.length) {
                return;
            }
            if (l == r) {
                seg[node] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            buildTree(2 * node, l, mid);
            buildTree(2 * node + 1, mid + 1, r);
            seg[node] = Math.min(seg[2 * node], seg[2 * node + 1]);
        }

        // finds min between a and b
        public int query(int node, int l, int r, int a, int b) {
            if (node >= seg.length) {
                return Integer.MAX_VALUE;
            }
            if (lazy[node] != 0) {
                seg[node] += lazy[node];
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
                lazy[node] = 0;
            }
            if (a <= l && r <= b) {
                return seg[node];
            }
            if (r < a || l > b) {
                return Integer.MAX_VALUE;
            }
            int m = (l + r) / 2;
            return Math.min(query(2 * node, l, m, a, b), query(2 * node + 1, m + 1, r, a, b));
        }

        // adds x to all values between a and b
        public void update(int node, int l, int r, int a, int b, int x) {
            if (node >= seg.length) {
                return;
            }
            if (lazy[node] != 0) {
                seg[node] += lazy[node];
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
                lazy[node] = 0;
            }
            if (a <= l && r <= b) {
                lazy[node] += x;
                return;
            }
            if (r < a || l > b) {
                return;
            }
            int m = (l + r) / 2;
            update(2 * node, l, m, a, b, x);
            update(2 * node + 1, m + 1, r, a, b, x);
        }
    }

}
